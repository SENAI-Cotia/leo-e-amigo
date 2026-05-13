package br.com.cinestar.controllers;

import br.com.cinestar.models.Review;
import br.com.cinestar.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/review")
    public String formReview(Model model){
        model.addAttribute("review",new Review());
        return "Review";
    }

    @GetMapping("/HomeReview")
    public String getReviews(Model model){
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews",reviews);
        return "Index";
    }

    @PostMapping("/salvarReview")
    public String cadastrar(@ModelAttribute Review ReviewR, RedirectAttributes redirectAttributes) {

        reviewRepository.save(ReviewR);

        redirectAttributes.addFlashAttribute("mensagemSucessoR","Review salva com sucesso!");
        return "redirect:/HomeReview";
    }

    @GetMapping("/editarReview/{id}")
    public String formEditar(@PathVariable Long id, Model model){

        Review review = reviewRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Review nao encontrada"));
        model.addAttribute("review",review);
        return "Review";

    }
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable long id){
        reviewRepository.deleteById(id);
        return "redirect:/HomeReview";
    }

}