package br.com.cinestar.controllers;

import br.com.cinestar.models.User;
import br.com.cinestar.repositories.UserRepository;

import br.com.cinestar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.cinestar.models.Review;
import br.com.cinestar.repositories.ReviewRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.lang.constant.ModuleDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String getUsers(Model model,
                           @RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout){
        model.addAttribute("user", new User());
        if (error != null) {
            model.addAttribute("mensagemErro", "Email ou senha inválidos. Tente novamente.");
        }
        if (logout != null) {
            model.addAttribute("mensagemSucesso", "Você saiu com sucesso.");
        }
        return "Login";
    }

    @GetMapping("/cadastro")
    public String formUser(Model model){
        model.addAttribute("user",new User());
        return "Cadastro";
    }

    @GetMapping("/editarUsuario/{id}")
    public String formEditarUsuario(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        model.addAttribute("user", user);
        return "EditarUsuario";
    }

    @PostMapping("/salvarEdicaoUsuario")
    public String salvarEdicaoUsuario(@ModelAttribute User userR,
                                      @RequestParam(value = "novaSenha", required = false) String novaSenha,
                                      RedirectAttributes redirectAttributes) {
        User userExistente = userRepository.findById(userR.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        userExistente.setNome(userR.getNome());
        userExistente.setUsername(userR.getUsername());
        userExistente.setEmail(userR.getEmail());

        if (novaSenha != null && !novaSenha.trim().isEmpty()) {
            userExistente.setSenha(passwordEncoder.encode(novaSenha));
        }

        userRepository.save(userExistente);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário atualizado com sucesso!");
        return "redirect:/Home";
    }

    @GetMapping("/Home")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean isAdmin = user.getRole().equals("ROLE_ADMIN");
        model.addAttribute("isAdmin", isAdmin);

        if (isAdmin) {
            List<User> users = userRepository.findAll()
                    .stream()
                    .filter(u -> !u.getRole().equals("ROLE_ADMIN"))
                    .collect(java.util.stream.Collectors.toList());
            model.addAttribute("users", users);
        } else {
            List<Review> reviews = reviewRepository.findByUser(user);
            model.addAttribute("reviews", reviews);
        }
        model.addAttribute("username", user.getUsername());

        return "Index";
    }

    @Autowired
    private UserService userService;

    @PostMapping("/salvar")
    public String cadastrar(@ModelAttribute User userR, RedirectAttributes redirectAttributes) {
        if (userR.getEmail().trim().isEmpty() || userR.getNome().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro", "ERRO: Digite um email e nome válidos");
            return "redirect:/cadastro";
        }

        if (userRepository.findByEmail(userR.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("mensagemErroEmail", "E-mail já existe.");
            return "redirect:/cadastro";
        }

        if (userRepository.findByUsername(userR.getUsername()).isPresent()) {
            redirectAttributes.addFlashAttribute("mensagemErroUsername", "Username já existe.");
            return "redirect:/cadastro";
        }

        userService.register(userR);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Usuário cadastrado com sucesso!");
        return "redirect:/";
    }

}
