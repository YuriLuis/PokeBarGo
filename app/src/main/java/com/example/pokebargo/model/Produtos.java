package com.example.pokebargo.model;

public class Produtos {
    private String nome;
    private int quantidade;
    private float valor;
    private String url_img;

    public Produtos() {
    }

    public Produtos(String nome, int quantidade, float valor, String url_img) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.url_img = url_img;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
