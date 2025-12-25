package br.com.lorran.practicing_lambda_expressions.p2maps;

import br.com.lorran.practicing_lambda_expressions.dto.ProdutoDTO;
import br.com.lorran.practicing_lambda_expressions.entities.Funcionario;
import br.com.lorran.practicing_lambda_expressions.entities.Pedido;
import br.com.lorran.practicing_lambda_expressions.entities.Pessoa;
import br.com.lorran.practicing_lambda_expressions.entities.Produto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExemploMaps {

    /*
        1. Converter uma lista de pessoas para uma lista com suas idades
            Dada a classe:

                class Pessoa {
                    private String nome;
                    private Integer idade;

                    public Pessoa(String nome, Integer idade) {
                        this.nome = nome;
                        this.idade = idade;
                    }
                }

        Tarefa: Use map() com uma Function<Pessoa, Integer> para obter apenas as idades.
    */
    @Test
    @DisplayName("")
    void exercicio1Map() {
        List<Pessoa> pessoas = List.of(
                new Pessoa("Ana", 25),
                new Pessoa("Carlos", 32)
        );

        Function<Pessoa, Integer> extrairIdade = p -> p.getIdade();

        List<Integer> idades = pessoas.stream()
                .map(extrairIdade)
                .toList();

        System.out.println(idades); // [25, 32]
    }

    /*
        2. Gerar lista de nomes em maiúsculas com prefixo "Cliente: "
            A mesma classe Pessoa acima.

        Tarefa: Gere uma lista com os nomes formatados como "Cliente: NOME" (em maiúsculas). Use map() com uma Function<Pessoa, String>.
    */
    @Test
    @DisplayName("")
    void exercicio2Map() {
        List<Pessoa> pessoas = List.of(
                new Pessoa("Ana", 25),
                new Pessoa("Carlos", 32)
        );

        Function<Pessoa, String> formatarNome = p -> "Cliente: " + p.getNome().toUpperCase();

        List<String> nomes = pessoas.stream()
                .map(formatarNome)
                .toList();

        System.out.println(nomes); // [Cliente: ANA, Cliente: CARLOS]
    }

    /*
        3. Converter uma lista de pedidos em uma lista de totais
        Classe de entrada:

            class Pedido {
                int id;
                double valor;

                public Pedido(int id, double valor) {
                    this.id = id;
                    this.valor = valor;
                }
            }

        Tarefa: Extraia os valores (valor) em uma List<Double> usando Function<Pedido, Double> com map().
    */
    @Test
    @DisplayName("")
    void exercicio3Map() {
        List<Pedido> pedidos = List.of(
                new Pedido(1, 120.0),
                new Pedido(2, 75.5)
        );

        Function<Pedido, Double> extrairValor = p -> p.getValor();

        List<Double> valores = pedidos.stream()
                .map(extrairValor)
                .toList();

        System.out.println(valores); // [120.0, 75.5]
    }

    /*
        4. Transformar uma lista de produtos em uma lista de DTOs

            class Produto {
                String nome;
                double preco;

                public Produto(String nome, double preco) {
                    this.nome = nome;
                    this.preco = preco;
                }
            }

            class ProdutoDTO {
                String nome;
                String precoFormatado;

                public ProdutoDTO(String nome, String precoFormatado) {
                    this.nome = nome;
                    this.precoFormatado = precoFormatado;
                }
            }

        Tarefa: Crie uma função que converte Produto em ProdutoDTO, formatando o preço como "R$ 99,99".
    */
    @Test
    @DisplayName("")
    void exercicio4Map() {
        List<Produto> produtos = List.of(
                new Produto("Mouse", 99.90),
                new Produto("Teclado", 150.00)
        );

        DecimalFormat df = new DecimalFormat("R$ #,##0.00");

        Function<Produto, ProdutoDTO> converterDTO = p ->
                new ProdutoDTO(p.getNome(), df.format(p.getPreco()));

        List<ProdutoDTO> dtos = produtos.stream()
                .map(converterDTO)
                .toList();

        dtos.forEach(dto -> System.out.println(dto.getNome() + ": " + dto.getPrecoFormatado()));
        /* Saída:
           Mouse: R$ 99,90
           Teclado: R$ 150,00
        */
    }

    /*
        5. Calcular imposto de uma lista de produtos

        Tarefa: A partir de uma lista de Produto, use map() com uma Function<Produto, Double> para gerar uma lista com o imposto de 12% de cada produto.
    */
    @Test
    @DisplayName("")
    void exercicio5Map() {
        List<Produto> produtos = List.of(
                new Produto("Mouse", 99.90),
                new Produto("Teclado", 150.00)
        );

        Function<Produto, Double> calcularImposto = p -> p.getPreco() * 0.12;

        List<Double> impostos = produtos.stream()
                .map(calcularImposto)
                .toList();

        System.out.println(impostos); // [11.988, 18.0]
    }

    /*
        6. Agrupar funcionários por idade em faixas etárias

            class Funcionario {
                String nome;
                int idade;
            }

        Tarefa: Crie uma Function<Funcionario, String> que retorna:

        - "Jovem" se idade < 30
        - "Adulto" se idade entre 30 e 50
        - "Sênior" se idade > 50

        Use Collectors.groupingBy() com essa função.
    */
    @Test
    void exercicio6Map() {
        List<Funcionario> funcionarios = List.of(
                new Funcionario("João", 25, 1200.0),
                new Funcionario("Paula", 40, 1200.0),
                new Funcionario("Marcos", 55, 1200.0)
        );

        Function<Funcionario, String> faixaEtaria = f -> {
            if (f.getIdade() < 30){
                return "Jovem";
            }
            else if (f.getIdade() <= 50){
                return "Adulto";
            }
            else return "Sênior";
        };

        Map<String, List<Funcionario>> agrupados = funcionarios.stream()
                .collect(Collectors.groupingBy(faixaEtaria));

        agrupados.forEach((faixa, lista) -> {
            System.out.println(faixa + ": " +
                    lista.stream().map(f -> f.getNome()).collect(Collectors.joining(", "))
            );
        });

        /* Saída esperada:
            Jovem: João
            Adulto: Paula
            Sênior: Marcos
         */
    }

    /*
        7. Aplicar encadeamento com andThen para aplicar desconto

            Function<Produto, Double> extrairPreco = p -> p.preco;
            Function<Double, Double> aplicarDesconto = preco -> preco * 0.9; // 10% de desconto

        Tarefa: Encadeie as duas funções usando andThen para obter os preços com desconto a partir de uma lista de produtos.
    */
    @Test
    void exercicio7Map() {
        List<Produto> produtos = List.of(
                new Produto("Mouse", 99.90),
                new Produto("Teclado", 150.00)
        );

        // Função para calcular desconto de 10% com BigDecimal
        Function<Produto, BigDecimal> precoComDesconto = p ->
                BigDecimal.valueOf(p.getPreco()) // converte de double para BigDecimal com segurança
                        .multiply(BigDecimal.valueOf(0.9)) // aplica 10% de desconto
                        .setScale(2, RoundingMode.HALF_EVEN); // define 2 casas decimais com arredondamento correto

        // Aplica no Stream
        List<BigDecimal> precosFinal = produtos.stream()
                .map(precoComDesconto)
                .toList();

        System.out.println(precosFinal); // [89.91, 135.00]
    }
}
