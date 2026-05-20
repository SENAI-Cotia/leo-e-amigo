package br.com.cinestar;

import br.com.cinestar.models.User;
import br.com.cinestar.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CinestarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinestarApplication.class, args);
    }

    @Bean
    public CommandLineRunner criarAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                User admin = new User();
                admin.setNome("admin");
                admin.setUsername("admin");
                admin.setEmail("admin@admin.com");
                admin.setSenha(passwordEncoder.encode("admin"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
            }
        };
    }
}