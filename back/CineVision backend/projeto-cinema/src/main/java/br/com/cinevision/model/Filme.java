package br.com.cinevision.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "filme")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filme")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "titulo_original")
    private String tituloOriginal;

    @Column(name = "sinopse")
    private String sinopse;

    @Column(name = "duracao_min")
    private Integer duracaoMin;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "data_estreia")
    private LocalDate dataEstreia;

    @Column(name = "url_poster")
    private String urlPoster;

    @Column(name = "url_trailer")
    private String urlTrailer;

    @Column(name = "ativo")
    private Boolean ativo = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "filme_genero",
            joinColumns = @JoinColumn(name = "id_filme"),
            inverseJoinColumns = @JoinColumn(name = "id_genero"))
    private List<Genero> generos = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Integer getDuracaoMin() {
        return duracaoMin;
    }

    public void setDuracaoMin(Integer duracaoMin) {
        this.duracaoMin = duracaoMin;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public LocalDate getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(LocalDate dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
