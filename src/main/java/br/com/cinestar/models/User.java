package br.com.cinestar.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
    @Table(name = "user")
    public class User {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private Long id;
        @Column(nullable = false,length=100)
        private String nome;
        @Column(nullable = false, unique = true)
        private String username;
        @Column(nullable = false, unique = true)
        private String email;
        @Column(nullable = false,length=100)
        private String senha;

        @OneToMany(mappedBy = "user")
        private List<Review> review;

        public User() {
        }

        public User(Long id, String nome, String username, String email, String senha) {
            this.id = id;
            this.nome = nome;
            this.username = username;
            this.email = email;
            this.senha = senha;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }
    }


