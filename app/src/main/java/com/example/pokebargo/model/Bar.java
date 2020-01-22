package com.example.pokebargo.model;

import java.util.List;

public class Bar {
    private String nome;
    private String url_img;
    private String descricao;
    private List<String> produto;
    private String endereco;
    private int classificacao;

    public Bar() {
    }

    public Bar(String nome, String url_img, String descricao, String endereco, int classificacao) {
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

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getProduto() {
        return produto;
    }

    public void setProduto(List<String> produto) {
        this.produto = produto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
}
