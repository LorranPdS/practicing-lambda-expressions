package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Produto {

    private String nome;
    private Double preco;

    public void precoComImposto(double imposto){
        this.preco = this.getPreco() + this.getPreco() * imposto;
    }
}
