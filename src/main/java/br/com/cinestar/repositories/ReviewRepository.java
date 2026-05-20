package br.com.cinestar.repositories;

import br.com.cinestar.models.Review;
import br.com.cinestar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByNomeFilme(String nomeFilme);
    List<Review> findByUser(User user);

    @Query("SELECT r FROM Review r WHERE r.user = :user ORDER BY r.nota DESC")
    List<Review> findTop10ByUserOrderByNotaDesc(@Param("user") User user, Pageable pageable);

    @Query("SELECT r.user, COUNT(r) as total FROM Review r GROUP BY r.user ORDER BY total DESC")
    List<Object[]> findTop10UsersWithMostReviews(Pageable pageable);
}
