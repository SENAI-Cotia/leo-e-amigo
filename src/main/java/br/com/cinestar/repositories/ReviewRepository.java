package br.com.cinestar.repositories;

import br.com.cinestar.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByNomeFilme(String nomeFilme);
}
