package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Funcionario {

    private String nome;
    private Integer idade;
    private Double salario;

    public void aumentar(double valor) {
        this.salario += valor;
    }

}
