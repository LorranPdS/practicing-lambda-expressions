package br.com.lorran.practicing_lambda_expressions.p3streams;

import br.com.lorran.practicing_lambda_expressions.entities.Funcionario;
import br.com.lorran.practicing_lambda_expressions.entities.Pedido;
import br.com.lorran.practicing_lambda_expressions.entities.Pessoa;
import br.com.lorran.practicing_lambda_expressions.entities.Produto;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Example2IntermediateStreamsTest {

    @Test
    void exercicio1PdfRetornarParesAndDobrarValores(){
        // Dada uma lista de inteiros, filtre apenas os pares e retorne o dobro de cada valor.
        List<Integer> numeroList = List.of(1, 3, 2, 9, 6, 10);
        List<Integer> numerosParesX2 = numeroList.stream()
                .filter(numero -> numero % 2 == 0)
                .map(resultado -> resultado * 2)
                .toList();

        System.out.println(numerosParesX2);
    }

    @Test
    void exercicio2PdfVerificarSeTodosOsNumerosDaListaSaoPositivos(){
        // Verifique se todos os números são positivos
        List<Integer> numeroList = List.of(1, 3, 2, -9, 6, 10);
        boolean isTodosPositivos = numeroList.stream().allMatch(numero -> numero > 0);
        System.out.println("Are all positive numbers? " + isTodosPositivos);
    }

    @Test
    void exercicio3PdfAgrupeFuncionariosPorIdade(){
        /* Agrupe funcionários por idade

           RESPOSTA IMPRESSA:
                        {34=[Funcionario(nome=Lorran, idade=34, salario=50000.0)],
                        21=[Funcionario(nome=Nicole, idade=21, salario=3200.0)],
                        26=[Funcionario(nome=Gisele, idade=26, salario=2500.0), Funcionario(nome=Douglas, idade=26, salario=3300.0)]}
         */

        List<Funcionario> funcionarioList = List.of(
                new Funcionario("Lorran", 34, 50_000.0),
                new Funcionario("Gisele", 26, 2_500.0),
                new Funcionario("Nicole", 21, 3_200.0),
                new Funcionario("Douglas", 26, 3_300.0));

        Map<Integer, List<Funcionario>> agrupadoPorIdade = funcionarioList.stream()
                .collect(Collectors.groupingBy(Funcionario::getIdade));

        System.out.println(agrupadoPorIdade);
    }

    @Test
    void exercicio4PdfSepareProdutosCarosDosBaratos(){
        /*
            Separe produtos caros (preço > 100) dos baratos

            RESPOSTA IMPRESSA:
            {false=[Produto(nome=Feijão, preco=10.0), Produto(nome=Macarrão, preco=12.0), Produto(nome=Farofa, preco=5.0)],
            true=[Produto(nome=Arroz, preco=105.0), Produto(nome=Café, preco=112.0)]}
         */
        List<Produto> produtoList = List.of(
                new Produto("Arroz", 105.0),
                new Produto("Feijão", 10.0),
                new Produto("Café", 112.0),
                new Produto("Macarrão", 12.0),
                new Produto("Farofa", 5.0));

        Map<Boolean, List<Produto>> produtosCarosDosBaratos = produtoList.stream()
                .collect(Collectors.partitioningBy(produto -> produto.getPreco() > 100));

        System.out.println(produtosCarosDosBaratos);
    }

    @Test
    void exercicio5PdfCalculeMediaSalarialDosFuncionarios() {
        /*
            Calcule a média salarial dos funcionários.

            RESPOSTA IMPRESSA: 3250.0
         */
        List<Funcionario> funcionarioList = List.of(
                new Funcionario("Dani", 34, 4_000.0),
                new Funcionario("Gisele", 26, 2_500.0),
                new Funcionario("Nicole", 21, 3_200.0),
                new Funcionario("Douglas", 26, 3_300.0));

        double mediaFuncionarios = funcionarioList.stream().collect(Collectors.averagingDouble(Funcionario::getSalario));
        System.out.println(mediaFuncionarios);
    }

    @Test
    void exercicioProprio_ExtrairNomeDosFuncionariosDaListaMantendoOrdem(){
        /*
            Extrair nome dos funcionários da lista mantendo a ordem e sem repetições

            RESPOSTA IMPRESSA: [Dani, Gisele, Nicole, Douglas]
         */
        List<Funcionario> funcionarioList = List.of(
                new Funcionario("Dani", 34, 4_000.0),
                new Funcionario("Gisele", 26, 2_500.0),
                new Funcionario("Nicole", 21, 3_200.0),
                new Funcionario("Douglas", 26, 3_300.0));

        Set<String> set = funcionarioList.stream()
                .map(Funcionario::getNome)
                .collect(Collectors.toCollection(LinkedHashSet::new));

        System.out.println(set);
    }

    @Test
    void exercicio6PdfSomarSalarioDosFuncionarios() {
        /*
            Some salários usando parallelStream

            RESPOSTA IMPRESSA: 14.000

            OBSERVAÇÃO: parallelStream é indicado apenas para grandes volumes de dados e operações independentes!
            Aqui é apenas para exercícios e conhecimento!
         */
        List<Funcionario> funcionarioList = List.of(
                new Funcionario("Dani", 34, 4_000.0),
                new Funcionario("Gisele", 26, 2_800.0),
                new Funcionario("Nicole", 21, 3_700.0),
                new Funcionario("Douglas", 26, 3_500.0));

        double somaSalarios = funcionarioList.parallelStream().mapToDouble(Funcionario::getSalario).sum();
        System.out.println(somaSalarios);
    }

    @Test
    void exercicio7PdfFiltrarPorPessoasMaioresDe18Anos() {
        // Filtrar por pessoas maiores de 18 anos.

        List<Pessoa> pessoaList = List.of(
                new Pessoa("Marcele", 17),
                new Pessoa("Jonata", 21),
                new Pessoa("Cibele", 23),
                new Pessoa("Cíntia", 15));

        List<Pessoa> maioresDe18Anos = pessoaList.stream().filter(pessoa -> pessoa.getIdade() > 18).toList();
        System.out.println(maioresDe18Anos);
    }

    @Test
    void exercicio8PdfRetornarUmMapComChaveNomeAndValorPreco() {
        // Retornar um Map de produto com key 'nome' e value 'preço'.
        List<Produto> produtoList = List.of(
                new Produto("Chinelo", 21.0),
                new Produto("Pote", 8.1),
                new Produto("Prato", 4.5));

        Map<String, Double> produtosMap = produtoList.stream().collect(Collectors.toMap(Produto::getNome, Produto::getPreco));
        System.out.println(produtosMap);
    }

    @Test
    void exercicio9PdfSomarValorTotalDosPedidos() {
        // Somar o valor total dos pedidos.
        List<Pedido> pedidoList = List.of(
                new Pedido(1, 1_000.0),
                new Pedido(2, 2_000.0),
                new Pedido(3, 3_000.0));

        double valorTotalPedidos = pedidoList.stream().mapToDouble(Pedido::getValor).sum();
        System.out.println(valorTotalPedidos);
    }
}
