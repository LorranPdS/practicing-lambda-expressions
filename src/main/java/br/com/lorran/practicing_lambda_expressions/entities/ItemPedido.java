package br.com.lorran.practicing_lambda_expressions.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ItemPedido {

    private String nome;

    @Getter
    private Double preco;
}
