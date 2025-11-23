package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Aluno {

    private String nome;
    private Double nota;
    private Double media;
    private Integer faltas;

    public Aluno(String nome, double nota){
        this.nome = nome;
        this.nota = nota;
    }

    public Aluno(String nome, double media, int faltas){
        this.nome = nome;
        this.media = media;
        this.faltas = faltas;
    }
}
