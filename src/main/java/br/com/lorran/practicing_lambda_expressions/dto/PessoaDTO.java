package br.com.lorran.practicing_lambda_expressions.dto;

import lombok.Getter;

@Getter
public class PessoaDTO {

    public String nomeCompleto;

    public PessoaDTO(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
