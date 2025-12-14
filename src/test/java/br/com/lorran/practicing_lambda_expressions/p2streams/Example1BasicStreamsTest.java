package br.com.lorran.practicing_lambda_expressions.p2streams;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Comparator;
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
            Dada a lista:
            List<List<Integer>> dados = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5)
            );

            Use flatMap para gerar uma única lista com todos os números.
         */

        List<List<Integer>> dados = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5)
        );

        List<Integer> listaUnicaDados = dados.stream().flatMap(List::stream).toList();
//        List<Integer> listaUnicaDadosOutraForma = dados.stream().flatMap(lista -> lista.stream()).toList(); // outra forma
        listaUnicaDados.forEach(System.out::println);
    }

    @Test
    void exercicio6StreamsDistinct(){
        /*
            Dada a lista:
            List<Integer> numeros = List.of(1, 2, 2, 3, 3, 3, 4);

            Remova números repetidos
         */
        List<Integer> numeros = List.of(1, 2, 2, 3, 3, 3, 4);
        var numerosUnicos = numeros.stream().distinct().toList();
        numerosUnicos.forEach(System.out::println);
    }

    @Test
    void exercicio7StreamsSorted1() {
        /*
            Dada a lista:
            List<String> animais = List.of("zebra", "ave", "cavalo", "elefante");

            Ordene as palavras por ordem alfabética
         */
        List<String> animais = List.of("zebra", "ave", "cavalo", "elefante");
        List<String> ordenacaoAnimais =  animais.stream().sorted().toList();
        ordenacaoAnimais.forEach(System.out::println);
    }

    @Test
    void exercicio8StreamsSorted2() {
        /*
            Dada a lista:
            List<String> animais = List.of("zebra", "ave", "cavalo", "elefante");

            Ordene pelo tamanho da palavra (da menor qtd de caracter para maior qtd de caracter)
         */
        List<String> animais = List.of("zebra", "ave", "cavalo", "elefante");
        List<String> ordenacaoPorTamanho = animais.stream().sorted(Comparator.comparingInt(String::length)).toList();
//        List<String> ordenacaoPorTamanhoOutraManeira = animais.stream().sorted(Comparator.comparingInt(animal -> animal.length())).toList();
        ordenacaoPorTamanho.forEach(System.out::println);
    }

    @Test
    void exercicio9StreamsSorted3() {
        /*
            Dada a lista:
            List<String> animais = List.of("zebra", "ave", "cavalo", "elefante");

            Ordene pelo tamanho da palavra em ordem decrescente (na ordem inversa, ou seja, primeiro a maior e depois a menor)
         */
        List<String> animais = List.of("zebra", "ave", "cavalo", "elefante");
        List<String> animaisOrdemDecrescente = animais.stream().sorted(Comparator.comparingInt(String::length).reversed()).toList();
        animaisOrdemDecrescente.forEach(System.out::println);
    }

    @Test
    void exercicio10StreamsLimit() {
        /*
            Dada a lista:
            List<Integer> numeros = List.of(10, 20, 30, 40, 50);

            Pegue apenas os 3 primeiros números da lista

            OBSERVAÇÃO: a nível de negócio, dificilmente fará sentido pegar por índice de lista, mas pelo conteúdo sim
         */
        List<Integer> numeros = List.of(10, 20, 30, 40, 50);
        List<Integer> primeiros3Numeros = numeros.stream().limit(3).toList();
        primeiros3Numeros.forEach(System.out::println);
    }

    @Test
    void exercicio11StreamsSkip() {
        /*
            Dada a lista:
            List<Integer> numeros = List.of(40, 10, 30, 50, 20);

            Pule os dois primeiros e pegue o resto
         */
        List<Integer> numeros = List.of(40, 10, 30, 50, 20);
        List<Integer> pula2Numeros = numeros.stream().skip(2).toList();
        pula2Numeros.forEach(System.out::println);
    }

    @Test
    void exercicio12StreamsPeek() {
        /*
            Dada a lista:
            List<Integer> numeros = List.of(40, 10, 30, 50, 20);

            Multiplique o valor dos conteúdos e faça um debug antes/depois de ocorrer a conversão da nova lista
         */
        List<Integer> numeros = List.of(10, 20, 30);

        List<Integer> resultado = numeros.stream()
                .peek(n -> System.out.println("Antes: " + n))
                .map(n -> n * 2)
                .peek(n -> System.out.println("Depois: " + n))
                .toList();

        System.out.println("Lista final:");
        System.out.println(resultado);
    }

    @Test
    void exercicio13StreamsCollectAndToList() {
        /*
            SEGUIR DO SEGUINTE EXERCÍCIO:
            8. collect() / toList()
            ️ Exercício 12 – Filtrar pares e retornar lista
         */
    }
}
