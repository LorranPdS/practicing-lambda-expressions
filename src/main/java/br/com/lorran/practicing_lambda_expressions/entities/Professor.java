package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Professor {

    private String nome;
    private String materia;
}
