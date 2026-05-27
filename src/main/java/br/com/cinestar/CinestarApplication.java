package br.com.cinestar;

import br.com.cinestar.models.Review;
import br.com.cinestar.models.User;
import br.com.cinestar.repositories.ReviewRepository;
import br.com.cinestar.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CinestarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinestarApplication.class, args);
    }

    @Bean
    public CommandLineRunner iniciarDados(UserRepository userRepository, ReviewRepository reviewRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            // ADMIN
            if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
                User admin = new User();
                admin.setNome("admin");
                admin.setUsername("admin");
                admin.setEmail("admin@admin.com");
                admin.setSenha(passwordEncoder.encode("admin@123"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
            }

            // REVIEWS
            List<Object[]> reviews = List.of(
                    new Object[]{"Carros",                 4.3, "https://upload.wikimedia.org/wikipedia/pt/thumb/9/9b/Carros_p%C3%B4ster.jpg/250px-Carros_p%C3%B4ster.jpg",                                                                "2024-03-10"},
                    new Object[]{"Toy Story",              4.2, "https://upload.wikimedia.org/wikipedia/pt/a/a7/Toy_Story_1995.jpg",                                                                                                        "2024-05-22"},
                    new Object[]{"Como Treinar seu Dragão",4.7, "https://br.web.img3.acsta.net/img/2c/59/2c5907be8f52c06b3cba679cd43d2ed7.jpg",                                                                                             "2024-01-15"},
                    new Object[]{"A Empregada",            3.9, "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQ36yQ0FzUxWxqOsHyFPd5aJINQMbdxjsIwxwYegMPFME_JhmYC",                                                              "2024-07-04"},
                    new Object[]{"Todo Mundo em Pânico",   3.8, "https://m.media-amazon.com/images/M/MV5BNTg0MDcxNmEtNDA5Yy00MDAyLWEzY2MtMzM4ZWMyNTUzN2Q4XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",                                          "2024-09-18"},
                    new Object[]{"Duna",                   4.1, "https://upload.wikimedia.org/wikipedia/pt/a/a3/Dune_2021.jpeg",                                                                                                            "2024-11-02"},
                    new Object[]{"It - A Coisa",           4.1, "https://br.web.img3.acsta.net/c_310_420/pictures/17/03/30/22/44/345288.jpg",                                                                                               "2024-06-30"},
                    new Object[]{"Gente Grande",           4.5, "https://br.web.img3.acsta.net/pictures/210/299/21029996_20130821205722213.jpg",                                                                                            "2024-02-14"},
                    new Object[]{"Jogador Nº1",            4.0, "https://m.media-amazon.com/images/M/MV5BNzVkMTgzODQtMWIwZC00NzE4LTgzZjYtMzAwM2I5OGZhNjE4XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg",                                         "2024-08-25"},
                    new Object[]{"La La Land",             3.5, "https://upload.wikimedia.org/wikipedia/pt/thumb/c/c0/La_La_Land_%28filme%29.png/250px-La_La_Land_%28filme%29.png",                                                        "2024-04-11"}
            );

            // USUARIOS
            List<Object[]> usuarios = List.of(
                    new Object[]{"Ana Silva",      "anasilva",    "ana@email.com"     },
                    new Object[]{"Bruno Costa",    "brunocosta",  "bruno@email.com"   },
                    new Object[]{"Carla Mendes",   "carlam",      "carla@email.com"   },
                    new Object[]{"Diego Rocha",    "diegor",      "diego@email.com"   },
                    new Object[]{"Elisa Ferreira", "elisaf",      "elisa@email.com"   },
                    new Object[]{"Felipe Nunes",   "felipen",     "felipe@email.com"  },
                    new Object[]{"Gabriela Lima",  "gabylima",    "gaby@email.com"    },
                    new Object[]{"Henrique Souza", "henriques",   "henrique@email.com"},
                    new Object[]{"Isabela Castro", "isabelac",    "isabela@email.com" },
                    new Object[]{"João Pereira",   "joaop",       "joao@email.com"    }
            );

            for (Object[] u : usuarios) {
                String email = (String) u[2];
                if (userRepository.findByEmail(email).isEmpty()) {
                    User user = new User();
                    user.setNome((String) u[0]);
                    user.setUsername((String) u[1]);
                    user.setEmail(email);
                    user.setSenha(passwordEncoder.encode("Senha@123"));
                    user.setRole("ROLE_USER");
                    userRepository.save(user);

                    for (Object[] r : reviews) {
                        Review review = new Review();
                        review.setNomeFilme((String) r[0]);
                        review.setNota((Double) r[1]);
                        review.setUrlCapaFilme((String) r[2]);
                        review.setDataAssistido(LocalDate.parse((String) r[3]));
                        review.setReviewDesc("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore.");
                        review.setUser(user);
                        reviewRepository.save(review);
                    }
                }
            }
        };
    }
}