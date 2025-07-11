package br.com.lorran.practicing_lambda_expressions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoDTO {

    private String nome;
    private String precoFormatado;
}
