package br.com.lorran.practicing_lambda_expressions.p1interfacefuncionalpadrao;

import br.com.lorran.practicing_lambda_expressions.dto.PessoaDTO;
import br.com.lorran.practicing_lambda_expressions.entities.Pessoa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
    Temos o método `apply` como principal método da Function
 */
public class Exemplo2Function {

    @Test
    @DisplayName("Convertendo String para Integer")
    void exercicio1Function(){
        Function<String, Integer> toLength = String::length;
        System.out.println(toLength.apply("ChatGPT")); // Saída: 7
    }

    @Test
    @DisplayName("Function que retorna o tamanho de cada String dentro da lista")
    void exercicio2Function(){
        List<String> nomes = Arrays.asList("Ana", "Joana", "Roberta");

        Function<String, Integer> tamanho = s -> s.length();

        List<Integer> tamanhos = nomes.stream()
                .map(tamanho)
                .collect(Collectors.toList());

        System.out.println(tamanhos); // Saída: [3, 5, 7]
    }

    /*
        'andThen' -> Executa a primeira função, depois a outra
        'compose' -> Executa a segunda função, antes da primeira
     */
    @Test
    @DisplayName("Compondo funções com andThen e compose")
    void exercicio3Function(){
        Function<Integer, Integer> dobrar = x -> x * 2;
        Function<Integer, Integer> somarDez = x -> x + 10;

        Function<Integer, Integer> dobrarDepoisSomar = dobrar.andThen(somarDez);
        Function<Integer, Integer> somarDepoisDobrar = dobrar.compose(somarDez);

        System.out.println(dobrarDepoisSomar.apply(5)); // (5 * 2) + 10 = 20
        System.out.println(somarDepoisDobrar.apply(5)); // (5 + 10) * 2 = 30
    }

    @Test
    @DisplayName("Criando uma função que retorna outra função")
    void exercicio4Function(){
        Function<Integer, Function<Integer, Integer>> somador = a -> b -> a + b;
        Function<Integer, Integer> somaCom5 = somador.apply(5);
        System.out.println(somaCom5.apply(3)); // Saída: 8
    }

    /*
        Implemente uma Function<Integer, Integer> que receba um número e retorne o dobro dele.
     */

    @Test
    @DisplayName("Function que recebe um número e retorna o dobro dele")
    void exercicio5Function(){
        Function<Integer, Integer> doubleFunction = v -> v * 2;
        System.out.println(doubleFunction.apply(2)); // Saída: 4
    }

    /*
        Crie uma Function<String, Integer> que receba um número em forma de string e o converta para inteiro.
     */
    @Test
    @DisplayName("Function que recebe um número em forma de String e converte para Integer")
    void exercicio6Function(){
        Function<String, Integer> paraInteiro = s -> Integer.parseInt(s);
        System.out.println(paraInteiro.apply("123")); // Saída: 123
    }

    /*
        Implemente uma Function<Boolean, String> que retorne "Verdadeiro" se for true e "Falso" se for false.
     */
    @Test
    @DisplayName("Function que retorna 'Verdadeiro' se for true e 'Falso' se for false")
    void exercicio7Function(){
        Function<Boolean, String> booleanoParaTexto = b -> b ? "Verdadeiro" : "Falso";
        System.out.println(booleanoParaTexto.apply(true)); // Saída: Verdadeiro
        System.out.println(booleanoParaTexto.apply(false)); // Saída: Falso
    }

    /*
        Implemente uma Function<String, String> que formate o nome de forma que as iniciais fiquem em letra maiúscula
     */
    @Test
    @DisplayName("Function que formate o nome de forma que as iniciais fiquem em letra maiúscula")
    void exercicio8Function(){
        Function<String, String> capitalizar = s -> Arrays.stream(s.split(" "))
                .map(p -> p.substring(0,1).toUpperCase() + p.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));

        System.out.println(capitalizar.apply("luana meireles")); // Saída: Luana Meireles
    }

    @Test
    @DisplayName("Uso de funções compostas 'andThen' e 'compose'")
    void exercicio9Function(){
        Function<Integer, Integer> dobrar = x -> x * 2;
        Function<Integer, Integer> somar10 = x -> x + 10;

        System.out.println(dobrar.andThen(somar10).apply(3)); // (3*2)+10 = 16
        System.out.println(dobrar.compose(somar10).apply(3)); // (3+10)*2 = 26

    }

    /*
        Implemente uma Function<String, String> que inverte o nome de todas as palavras, como uma leitura ao contrário
     */
    @Test
    @DisplayName("Function que inverte o nome de todas as palavras, como uma leitura ao contrário")
    void exercicio10Function(){
        Function<String, String> inverter = s -> new StringBuilder(s).reverse().toString();
        System.out.println(inverter.apply("java")); // Saída: avaj
    }

    /*
        Implemente uma Function<LocalDate, String> que obtém o ano de uma data informada
     */
    @Test
    @DisplayName("Function que obtém o ano de uma data informada")
    void exercicio11Function(){
        Function<LocalDate, Integer> ano = LocalDate::getYear;
        System.out.println(ano.apply(LocalDate.of(2020, 5, 10))); // Saída: 2020
    }

    @Test
    @DisplayName("Encadeamento dinâmico")
    void exercicio12Function(){
        List<String> nomes = List.of("Ana", "Luisa");
        List<Integer> tamanhos = mapList(nomes, s -> s.length());
        System.out.println(tamanhos); // Saída: [3, 5]

    }

    public static <T, R> List<R> mapList(List<T> lista, Function<T, R> mapper) {
        return lista.stream().map(mapper).toList();
    }

    @Test
    @DisplayName("Validação de estrutura de CPF")
    void exercicio13Function(){
        Function<String, Boolean> validarCpf = cpf -> cpf != null && cpf.matches("\\d{11}");
        System.out.println(validarCpf.apply("12345678901")); // Saída: true
        System.out.println(validarCpf.apply("123-456-789-01")); // Saída: false
    }

    @Test
    void exercicio14Function(){
        Function<Pessoa, PessoaDTO> converter = p -> new PessoaDTO(p.getNome());
        Pessoa pessoa = new Pessoa("Rosa Oliveira", 30);
        PessoaDTO dto = converter.apply(pessoa);
        System.out.println(dto.nomeCompleto); // Saída: Rosa Oliveira
    }

    /*
        A interface Function<T, R> é amplamente usada dentro da API de Streams do Java, principalmente com o
            método map() — mas também aparece em vários outros contextos de transformação de dados.

            Resumo direto:
            Você usa Function<T, R> sempre que quiser transformar um elemento em outro dentro de um Stream.

            Métodos que usam Function em Streams
            - map(Function<T, R>)	Transforma os elementos de T em R
            - flatMap(Function<T, Stream<R>>)	Transforma e "achata" os elementos
            - collect(Collectors.mapping(Function, Collector))	Mapeia antes de coletar
     */

    @Test
    @DisplayName("Transformando nomes em letras maiúsculas")
    void exercicio15Function(){
        List<String> nomes = List.of("ana", "joão", "bia");

        List<String> maiusculos = nomes.stream()
                .map(String::toUpperCase) // Function<String, String>
                .toList();

        System.out.println(maiusculos); // [ANA, JOÃO, BIA]
    }

    @Test
    @DisplayName("map() com conversão de tipos de String para Integer")
    void exercicio16Function(){
        List<String> numeros = List.of("1", "2", "3");

        List<Integer> inteiros = numeros.stream()
                .map(Integer::parseInt) // Function<String, Integer>
                .toList();

        System.out.println(inteiros); // [1, 2, 3]
    }

    @Test
    @DisplayName("flatMap() — transformar e 'achatar' - separar palavras de frases")
    void exercicio17Function(){
        List<String> frases = List.of("Olá mundo", "Java é legal");

        List<String> palavras = frases.stream()
                .flatMap(frase -> Arrays.stream(frase.split(" "))) // Function<String, Stream<String>>
                .toList();

        System.out.println(palavras); // [Olá, mundo, Java, é, legal]
    }

    @Test
    @DisplayName("collect() com Collectors.mapping - transformar nomes e juntar com vírgula")
    void exercicio18Function(){
        String resultado = List.of("ana", "bia", "carlos").stream()
                .collect(Collectors.mapping(String::toUpperCase, Collectors.joining(", ")));

        System.out.println(resultado); // ANA, BIA, CARLOS
    }

    /**
     * Seguem os exercícios envolvendo a classe {@link br.com.lorran.practicing_lambda_expressions.p2maps.ExemploMaps}
     */
}
