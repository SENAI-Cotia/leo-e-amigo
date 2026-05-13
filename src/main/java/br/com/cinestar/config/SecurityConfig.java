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
                .requestMatchers("/", "/cadastro","/login").permitAll() // Rota publicas
                .anyRequest().authenticated() // Qualquer rota ele vai privar
        ).formLogin(form -> form
                .loginPage("/")
                .defaultSuccessUrl("/Home", true) // Sempre redireciona para pagina home quando o login é feito com sucesso
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/?logout")
                .permitAll()
        );

        return http.build();
    }
}