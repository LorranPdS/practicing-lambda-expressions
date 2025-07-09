package br.com.lorran.practicing_lambda_expressions.dto;

import lombok.Getter;

@Getter
public class PersonDTO {

    public String nomeCompleto;

    public PersonDTO(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
