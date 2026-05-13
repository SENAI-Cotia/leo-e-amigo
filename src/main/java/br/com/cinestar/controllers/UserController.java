package br.com.cinestar.controllers;

import br.com.cinestar.models.User;
import br.com.cinestar.repositories.UserRepository;

import br.com.cinestar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.constant.ModuleDesc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("user",new User());
        return "Login";
    }

    @GetMapping("/cadastro")
    public String formUser(Model model){
        model.addAttribute("user",new User());
        return "Cadastro";
    }

    @GetMapping("/Home")
    public String home(Model model){
    List <User> users =userRepository.findAll();
    model.addAttribute("users",users);
    return "Index";
    }

//    @PostMapping("/login")
//    public String logar(@ModelAttribute User userR){
//        User user=userRepository.findByEmail(userR.getEmail()).orElseThrow(()-> new RuntimeException("Credenciais inválidas"));
//        if (!user.getSenha().equals(userR.getSenha())){
//            throw new RuntimeException("Credenciais inválidas");
//        }
//        return "redirect:/Home";
//    }

    @Autowired
    private UserService userService;

// REMOVER o @Autowired do UserRepository se não usar mais em outros métodos

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
