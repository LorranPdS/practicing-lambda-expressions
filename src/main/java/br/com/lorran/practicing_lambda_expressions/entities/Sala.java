package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.Getter;

public class Sala {

    @Getter
    private String nome;
    private boolean isLimpa;

    public Sala(String nome) {
        this.nome = nome;
        this.isLimpa = false;
    }

    public boolean isLimpa() {
        return isLimpa;
    }

    public void limpar() {
        this.isLimpa = true;
    }
}
