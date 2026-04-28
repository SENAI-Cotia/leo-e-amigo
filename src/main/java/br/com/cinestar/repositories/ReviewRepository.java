package br.com.cinestar.repositories;

import br.com.cinestar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository<User, Long> {
    Optional<User> findByNome_filme(String nome_filme);
}
