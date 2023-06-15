package com.example.mangastorecliente.Model;

public class Manga {

    private int id;

    private String anime;

    private String edicao;

    private String titulo;

    private int quantidade;

    private double preco;

    public Manga(String anime, String edicao, String titulo, double preco) {
        this.anime = anime;
        this.edicao = edicao;
        this.titulo = titulo;
        this.preco = preco;
    }



    public Manga(int id, String anime, String edicao, String titulo,int quantidade, double preco) {
        this.id = id;
        this.anime = anime;
        this.edicao = edicao;
        this.titulo = titulo;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Manga() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
