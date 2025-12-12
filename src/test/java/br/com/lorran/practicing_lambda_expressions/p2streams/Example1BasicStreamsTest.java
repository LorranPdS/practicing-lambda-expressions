package br.com.lorran.practicing_lambda_expressions.p2streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class Example1BasicStreamsTest {

    @Test
    void exercicio1StreamsFilter(){
        /*
            Dada a lista:
            List<Integer> nums = List.of(1, 10, 22, 5, 7, 30, 2);

            Filtre apenas os números maiores que 10.
         */
        List<Integer> numeros = List.of(1, 10, 22, 5, 7, 30, 2);
        List<Integer> maioresQue10 = numeros.stream().filter(filtro -> filtro > 10).toList();
        maioresQue10.forEach(System.out::println);
    }

    @Test
    void exercicio2StreamsFilter2(){
        /*
            Dada a lista:
            List<Integer> nums = List.of(1, 10, 22, 5, 7, 30, 2);

            Filtre apenas os números pares.
         */
        List<Integer> numeros = List.of(1, 10, 22, 5, 7, 30, 2);
        List<Integer> numerosPares = numeros.stream().filter(numero -> numero % 2 == 0).toList();
        numerosPares.forEach(System.out::println);
    }

    @Test
    void exercicio3StreamsMap(){
        /*
            Dada a lista:
            List<Integer> nums = List.of(2, 3, 4, 5);

            Transforme cada número da lista acima em seu quadrado.
         */
        List<Integer> numeros = List.of(2, 3, 4, 5);
        List<Integer> dobroNumeros = numeros.stream().map(num -> num * num).toList();
        dobroNumeros.forEach(System.out::println);
    }

    @Test
    void exercicio4StreamsMap(){
        /*
            Dada a lista:
            List<String> nomes = List.of("Ana", "Bruno", "Carolina");

            Transforme uma lista de nomes em seus tamanhos:
         */
        List<String> nomes = List.of("Ana", "Bruno", "Carolina");
        List<Integer> tamanhoNomes = nomes.stream().map(String::length).toList();
        System.out.println(tamanhoNomes);
    }

    @Test
    void exercicio5StreamsFlatMap(){
        /*
            Assunto no ChatGPT: Streams em Java: explicação
            CONTINUAR DO 3. flatMap -> exercício 5
         */

    }
}
