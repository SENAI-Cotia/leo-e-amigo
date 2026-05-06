package br.com.cinestar.controllers;

import br.com.cinestar.models.Review;
import br.com.cinestar.models.User;
import br.com.cinestar.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/review")
    public String formReview(Model model){
        model.addAttribute("review",new Review());
        return "Review";
    }

    @GetMapping("/Home")
    public String getReviews(Model model){
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews",reviews);
        return "Index";
    }

    @PostMapping("/salvar")
    public String cadastrar(@ModelAttribute Review ReviewR, RedirectAttributes redirectAttributes) {
        if (ReviewR.getNomeFilme().trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErroR", "ERRO: Digite o nome do filme");
            return "redirect:/review";
        }

        reviewRepository.save(ReviewR);

        redirectAttributes.addFlashAttribute("mensagemSucessoR","Review salva com sucesso!");
        return "redirect:/";
    }



}