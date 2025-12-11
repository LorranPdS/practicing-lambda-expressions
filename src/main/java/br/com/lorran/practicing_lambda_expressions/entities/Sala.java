package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Sala {

    @Getter
    private String nome;
    private boolean isLimpa;
    @Setter
    private String horario;

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
