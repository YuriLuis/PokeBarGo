package com.example.pokebargo.model;

/**
 * Classe modelo para cada contato
 *
 * Nesta classe temos os atributos e verificações de setters && getters
 *
 *
 * @author Yuri Luis Garcia Pereira <yuri.luizg@hotmail.com>
 * @since 1.0.0
 *
 */
public class Contato extends Usuario {

    private String numeroContato;
    private String nomeContato;

    /**
     * Construtor da classe para atribuir as informações ao atributo
     *
     * @author Yuri Luis Garcia Pereira <yuri.luizg@hotmail.com>
     * @since 1.0.0
     *
     * @param numeroContato que recebe o numero do contato
     * @param nomeContato que recebe o nome do contato
     */
    public Contato(String numeroContato, String nomeContato){
        setNomeContato(nomeContato);
        setNumeroContato(numeroContato);
    }
    /**
     * Método que retorna o numero do contato
     * @return String numeroContato
     */
    public String getNumeroContato() {
        return numeroContato;
    }

    /**
     * Método que atribui o valor do parâmetro ao atributo global numeroContato
     *
     * @param numeroContato
     */
    public void setNumeroContato(String numeroContato) {
        if(!numeroContato.equals(null) || !numeroContato.equals(""))
            this.numeroContato = numeroContato;
    }

    /**
     * Método que retorna o nome do contato
     * @return String nomeContato
     */
    public String getNomeContato() {
        return nomeContato;
    }

    /**
     * Método que atribui o valor do parâmetro ao atributo global nomeContato
     * @param nomeContato
     */
    public void setNomeContato(String nomeContato) {
        if(!nomeContato.equals(null) || !nomeContato.equals(""))
            this.nomeContato = nomeContato;
    }
}
