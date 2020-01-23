package com.example.pokebargo.model;

public class Produto {
    private String nome;
    private double valor;
    private int url_img;

    public Produto() {
    }

    public Produto(String nome, double valor, int url_img) {
        this.nome = nome;
        this.valor = valor;
        this.url_img = url_img;
    }

    public int getUrl_img() {
        return url_img;
    }

    public void setUrl_img(int url_img) {
        this.url_img = url_img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
