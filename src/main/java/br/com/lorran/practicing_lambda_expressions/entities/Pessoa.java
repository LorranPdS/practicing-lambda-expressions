package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pessoa {

    private String nome;
    private Integer idade;
}
