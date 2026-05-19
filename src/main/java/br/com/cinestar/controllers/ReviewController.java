package br.com.cinestar.controllers;

import br.com.cinestar.models.Review;
import br.com.cinestar.models.User;
import br.com.cinestar.repositories.ReviewRepository;
import br.com.cinestar.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/review")
    public String formReview(Model model){
        model.addAttribute("review", new Review());
        return "Review";
    }

    @GetMapping("/HomeReview")
    public String getReviews(Model model, @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        List<Review> reviews = reviewRepository.findByUser(user);
        model.addAttribute("reviews", reviews);
        return "Index";
    }

    @PostMapping("/salvarReview")
    public String cadastrar(@ModelAttribute Review ReviewR,
                            @AuthenticationPrincipal UserDetails userDetails,
                            RedirectAttributes redirectAttributes) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ReviewR.setUser(user);
        reviewRepository.save(ReviewR);

        redirectAttributes.addFlashAttribute("mensagemSucessoR", "Review salva com sucesso!");
        return "redirect:/HomeReview";
    }

    @GetMapping("/editarReview/{id}")
    public String formEditar(@PathVariable Long id, Model model,
                             @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));
        if (!review.getUser().getId().equals(user.getId())) {
            return "redirect:/HomeReview";
        }
        model.addAttribute("review", review);
        return "Review";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id,
                          @AuthenticationPrincipal UserDetails userDetails){
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review não encontrada"));
        if (review.getUser().getId().equals(user.getId())) {
            reviewRepository.deleteById(id);
        }
        return "redirect:/HomeReview";
    }
}