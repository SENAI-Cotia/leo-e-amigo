package br.com.cinestar.models;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
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
    @Column(nullable = false, unique = false)
    private String urlCapaFilme;
    @Column(nullable = true, unique = false)
    private LocalDate dataAssistido;

    @ManyToOne
    private User user;

    public Review() {
    }

    public Review(Long id, String nomeFilme, String reviewDesc, Double nota, String urlCapaFilme, LocalDate dataAssistido) {
        this.id = id;
        this.nomeFilme = nomeFilme;
        this.reviewDesc = reviewDesc;
        this.nota = nota;
        this.urlCapaFilme = urlCapaFilme;
        this.dataAssistido = dataAssistido;
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

    public String getUrlCapaFilme() {
        return urlCapaFilme;
    }

    public void setUrlCapaFilme(String urlCapaFilme) {
        this.urlCapaFilme = urlCapaFilme;
    }

    public LocalDate getDataAssistido() {
        return dataAssistido;
    }

    public void setDataAssistido(LocalDate dataAssistido) {
        this.dataAssistido = dataAssistido;
    }
}


