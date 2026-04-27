package br.com.cinestar.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nomeFilme;
    @Column(nullable = true, unique = false)
    private String reviewDesc;
    @Column(nullable = false, unique = false)
    private Double nota;

    @ManyToOne
    private User user;

    public Review() {
    }

    public Review(Long id, String nomeFilme, String reviewDesc, Double nota) {
        this.id = id;
        this.nomeFilme = nomeFilme;
        this.reviewDesc = reviewDesc;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}


