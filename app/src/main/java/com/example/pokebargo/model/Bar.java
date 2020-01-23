package com.example.pokebargo.model;

import java.util.List;

public class Bar {
    private String nome;
    private int url_img;
    private String descricao;
    private List<Produto> produto;
    private String endereco;
    private float classificacao;

    public Bar(String nome, int url_img, String descricao, List<Produto> produto, String endereco, float classificacao) {
        this.nome = nome;
        this.url_img = url_img;
        this.descricao = descricao;
        this.produto = produto;
        this.endereco = endereco;
        this.classificacao = classificacao;
    }

    public Bar() {
    }

    public Bar(String nome, int url_img, String descricao, String endereco, int classificacao) {
        this.nome = nome;
        this.url_img = url_img;
        this.descricao = descricao;
        this.endereco = endereco;
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getUrl_img() {
        return url_img;
    }

    public void setUrl_img(int url_img) {
        this.url_img = url_img;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProduto() {
        return produto;
    }

    public void setProduto(List<Produto> produto) {
        this.produto = produto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }
}
