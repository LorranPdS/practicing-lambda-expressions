package br.com.lorran.practicing_lambda_expressions.p3streams;

import br.com.lorran.practicing_lambda_expressions.entities.Funcionario;
import br.com.lorran.practicing_lambda_expressions.entities.Produto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class Example2IntermediateStreamsTest {

    @Test
    void exercicio1PdfRetornarParesAndDobrarValores(){
        // Dada uma lista de inteiros, filtre apenas os pares e retorne o dobro de cada valor.
        List<Integer> numerosList = List.of(1, 3, 2, 9, 6, 10);
        List<Integer> numerosParesX2 = numerosList.stream()
                .filter(numero -> numero % 2 == 0)
                .map(resultado -> resultado * 2)
                .toList();

        System.out.println(numerosParesX2);
    }

    @Test
    void exercicio2PdfVerificarSeTodosOsNumerosDaListaSaoPositivos(){
        // Verifique se todos os números são positivos
        List<Integer> numerosList = List.of(1, 3, 2, -9, 6, 10);
        boolean isTodosPositivos = numerosList.stream().allMatch(numero -> numero > 0);
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

        List<Funcionario> funcionariosList = List.of(
                new Funcionario("Lorran", 34, 50_000.0),
                new Funcionario("Gisele", 26, 2_500.0),
                new Funcionario("Nicole", 21, 3_200.0),
                new Funcionario("Douglas", 26, 3_300.0));

        Map<Integer, List<Funcionario>> agrupadoPorIdade = funcionariosList.stream()
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
        List<Produto> produtosList = List.of(
                new Produto("Arroz", 105.0),
                new Produto("Feijão", 10.0),
                new Produto("Café", 112.0),
                new Produto("Macarrão", 12.0),
                new Produto("Farofa", 5.0));

        Map<Boolean, List<Produto>> produtosCarosDosBaratos = produtosList.stream()
                .collect(Collectors.partitioningBy(produto -> produto.getPreco() > 100));

        System.out.println(produtosCarosDosBaratos);
    }

    @Test
    void exercicio5PdfSepareProdutosCarosDosBaratos() {
        /*
            Calcule a média salarial dos funcionários.

            RESPOSTA IMPRESSA: FAZER ESSE EXERCÍCIO DO PDF

         */
        List<Funcionario> funcionariosList = List.of(
                new Funcionario("Lorran", 34, 50_000.0),
                new Funcionario("Gisele", 26, 2_500.0),
                new Funcionario("Nicole", 21, 3_200.0),
                new Funcionario("Douglas", 26, 3_300.0));
    }
}
