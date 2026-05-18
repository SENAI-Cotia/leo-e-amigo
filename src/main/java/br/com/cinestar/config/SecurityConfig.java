package br.com.cinestar.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/cadastro", "/","/salvar").permitAll() // Rota publicas
                .requestMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().authenticated() // Qualquer rota ele vai privar
        ).formLogin(form -> form
                .loginPage("/")
                .loginProcessingUrl("/login")   // <- essa linha garante que o Security intercepta o POST /login
                .defaultSuccessUrl("/Home", true)
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/?logout")
                .permitAll()
        );

        return http.build();
    }
}