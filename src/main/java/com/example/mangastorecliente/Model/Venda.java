package com.example.mangastorecliente.Model;

import java.util.List;

public class Venda {
    private int numero;
    private int itens;
    private double preco;
    private int idComprador;
    private String situacao;

    public Venda() {
    }

    public Venda(int numero, int itens, double preco, int idComprador,String situacao) {
        this.numero = numero;
        this.itens = itens;
        this.preco = preco;
        this.idComprador = idComprador;
        this.situacao = situacao;
    }

    public Venda(int itens, double preco, int idComprador, String situacao) {
        this.itens = itens;
        this.preco = preco;
        this.idComprador = idComprador;
        this.situacao = situacao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getItens() {
        return itens;
    }

    public void setItens(int itens) {
        this.itens = itens;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
