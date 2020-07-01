package br.com.locadora.clientes;

import br.com.locadora.inicializacao.BancoDeDados;

public class Clientes {

    private String nome;
    private String cpf;
    private boolean possuiLocacao;

    public Clientes(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.possuiLocacao = false;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isPossuiLocacao() {
        return possuiLocacao;
    }

    public void locou() {
        this.possuiLocacao = true;
    }

    public void devolveu() {
        this.possuiLocacao = false;
    }
}
