package br.com.lorran.practicing_lambda_expressions.p3streams;

import br.com.lorran.practicing_lambda_expressions.entities.Funcionario;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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
            Dada a lista:
            List<Integer> numeros = List.of(1, 4, 13, 15, 8, 10);


            ️ Filtre números pares e retorne uma nova lista
         */
        List<Integer> numerosList = List.of(1, 4, 13, 15, 8, 10);

        List<Integer> paresList = numerosList.stream()
                .filter(n -> n % 2 == 0)
                .toList(); // ou .collect(Collectors.toList())

        System.out.println(paresList);
    }

    @Test
    void exercicio14StreamsSomandoIntegers() {
        // Explicando o que é o reduce: https://www.baeldung.com/java-stream-reduce

        /*
            Dada a lista:
            List<Integer> numerosList = List.of(2, 1, 3, 4, 5, 6); // total = 21

            ️ Some todos os números da lista
         */

        List<Integer> numerosList = List.of(2, 1, 3, 4, 5, 6);
//        int result = numerosList.stream().reduce(0, (subtotal, element) -> subtotal + element);
//        var somaUmValorSoh = numerosList.stream().reduce(0, (acumulador, proximoValor) -> acumulador + proximoValor); // outra forma de explicação

        int result = numerosList.stream().reduce(0, Integer::sum);
//        assertThat(result).isEqualTo(21);
        System.out.println(result);
        /*
            O que aconteceu basicamente foi o seguinte:
            int result = numerosList.stream().reduce(0, (subtotal, element) -> subtotal + element);

            - reduce (0 >>> o valor começa valendo zero
            - subtotal >>> iniciará valendo o valor exibido na identity, que no caso foi zero
            - element >>> será o primeiro elemento da lista, que na nossa lista é o número 2
            - subtotal + element >>> lógica a ser feita, que no caso foi somar subtotal (0) com element (2)
            - próximo >>> subtotal = 2 / element = 1 (é o próximo da lista) / resultado 3
            - próximo >>> subtotal = 3 / element 3 (é o próximo da lista) / resultado 6
            - próximo >>> subtotal = 6 / element 4 (é o próximo da lista) / resultado 10
            - próximo >>> subtotal = 10 / element 5 (é o próximo da lista) / resultado 15
            - próximo >>> subtotal = 15 / element 6 (é o próximo da lista) / resultado 21

            PORTANTO, o que o reduce() faz é o seguinte:
                Combina todos os elementos de um stream em um único resultado, usando uma função de acumulação fornecida
                pelo desenvolvedor para definir como essa junção acontece
         */
    }

    @Test
    void exercicio15StreamsConcatenandoStrings() {
        /*
            Dada a lista:
            List<String> palavras = List.of("Olá", " ", "Mundo", "!");

            Concatene toda a frase
         */

        List<String> palavras = List.of("Olá", " ", "Mundo", "!");
//        var frase = palavras.stream().reduce("", (palavraInicial, proximaPalavra) -> palavraInicial + proximaPalavra);
        var frase = palavras.stream().reduce("", String::concat);
        System.out.println(frase);
    }

    @Test
    void exercicio16StreamsEncontreOMaiorNumero() {
        /*
            Dada a lista:
            List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12); // maior = 71

            Encontre o maior número
         */
        List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);

        int maiorNumero = numerosList.stream().reduce(0, Integer::max);

        System.out.println(maiorNumero);
        /*
            IMPORTANTE:
            int maiorNumero = numerosList.stream().reduce(0, Integer::max);
            - veja que iniciei o valor com zero, então se não houver conteúdo na lista, ele irá me retornar zero

            Mas e se eu quiser que estoure uma exceção caso a lista não tenha valor?
            Então uso a propriedade do Optional para lançar exceção, o que dependendo do plano de negócio seria o mais correto:

            int maiorNumero = numerosList.stream().
                reduce(Integer::max).orElseThrow(() -> new NoSuchElementException("Não há elementos na lista"));
         */
    }

    @Test
    void exercicio17StreamsUsandoObjetosSomaIdades() {
        /*
            Dada a lista:
            List<Funcionario> funcionarios = List.of(
                                            new Funcionario("John", 30, 2500.0),
                                            new Funcionario("Michael", 26, 3000.0));

            Some a idade dos funcionários
         */

        List<Funcionario> funcionarios = List.of(
                                            new Funcionario("John", 30, 2500.0),
                                            new Funcionario("Michael", 26, 3000.0));
        /*
        int somaIdadeFuncionarios = funcionarios.stream().reduce
                (0, (idadeInicial, idadeFinal) -> idadeInicial + idadeFinal.getIdade()); // ele não compila porque os tipos dos argumentos do acumulador são int e Funcionario.

                Isso é explicado no link https://www.baeldung.com/java-stream-reduce no trecho "Curiosamente, este código não compila"
         */
        int somaIdadeFuncionarios = funcionarios.stream().reduce
                (0, (idadeInicial, idadeFinal) -> idadeInicial + idadeFinal.getIdade(), Integer::sum);
    }

    @Test
    void exercicio18StreamsUsandoObjetosSomaSalarios() {
        /*
            Dada a lista:
            List<Funcionario> funcionarios = List.of(
                                            new Funcionario("John", 30, 2500.0),
                                            new Funcionario("Michael", 26, 3000.0));

            Some o salário dos funcionários
         */

        List<Funcionario> funcionarios = List.of(
                new Funcionario("John", 30, 2500.0),
                new Funcionario("Michael", 26, 3000.0));

//        double somaSalarioFuncionarios = funcionarios.stream().map(Funcionario::getSalario).reduce(0.0, (valorAcumulado, proximoValor) -> valorAcumulado + proximoValor);
        double somaSalarioFuncionarios = funcionarios.stream().map(Funcionario::getSalario).reduce(0.0, Double::sum);
        System.out.println(somaSalarioFuncionarios);
    }

    @Test
    void exercicio19StreamsVerificarSeTodosOsNumerosSaoPositivos() {
        /*
            Dada a lista:
            List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);

            Verifique se todos os números são positivos
         */
        List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);
        boolean isPositivo = numerosList.stream().allMatch(numero -> numero > 0);
        System.out.println("Todos os números da lista são positivos? " + isPositivo);
    }

    @Test
    void exercicio20StreamsVerificarSeExisteAlgumNumeroMaiorQue50() {
        /*
            Dada a lista:
            List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);

            Verifique se há algum número maior que 50 na lista
         */
        List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);
        boolean hasMaiorQue50 = numerosList.stream().anyMatch(numero -> numero > 50);
        System.out.println("Há número maior que 50 na lista? " + hasMaiorQue50);
    }

    @Test
    void exercicio21StreamsVerificarSeNenhumFuncionarioRecebeMenosQue2500() {
        /*
            Dada a lista:
            List<Funcionario> funcionariosList = List.of(
                                                new Funcionario("Ana", 28, 3000.0),
                                                new Funcionario("Bruno", 35, 4500.0),
                                                new Funcionario("Carla", 22, 2800.0));


            Verifique se nenhum funcionário da lista recebe salário menor que 2.500
            RESPOSTA: true
         */
        List<Funcionario> funcionariosList = List.of(
                new Funcionario("Ana", 28, 3000.0),
                new Funcionario("Bruno", 35, 4500.0),
                new Funcionario("Carla", 22, 2800.0));

        boolean salarioMenorQue2500 = funcionariosList
                .stream()
                .noneMatch(funcionario -> funcionario.getSalario() < 2500);

        System.out.println("Nenhum funcionário recebe menos que 2500? " + salarioMenorQue2500);
        /*
            Ou seja, queremos responder à pergunta:
            “Existe alguém com salário abaixo de 2.500?”
                Se a resposta for não, então o noneMatch deve retornar true.

             Dica mental: noneMatch(condição) = !anyMatch(condição)
             EXEMPLO:
             boolean nenhumSalarioBaixo = !funcionarios.stream().anyMatch(f -> f.getSalario() < 2500);
         */
    }

    @Test
    void exercicio22StreamsObterPrimeiroNumeroMaiorQue10() {
        /*
            Dada a lista:
            List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);

            Obtenha o primeiro número maior que 10
            RESPOSTA: 32
         */
        List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);
        Integer primeiroNumeroMaiorQue10 = numerosList.stream()
                .filter(numero -> numero > 10)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Não há número maior que 10 na lista"));

        System.out.println("Primeiro número maior que 10: " + primeiroNumeroMaiorQue10);
    }

    @Test
    void exercicio23StreamsObterFuncionarioComSalarioMaiorQue4000() {
        /*
            Dada a lista:
            List<Funcionario> funcionariosList = List.of(
                                new Funcionario("Ana", 28, 3000.0),
                                new Funcionario("Bruno", 35, 4500.0),
                                new Funcionario("Carla", 22, 2800.0),
                                new Funcionario("Daniel", 40, 6000.0));


            Encontre qualquer funcionário que tenha salário maior que 4.000.
         */
        List<Funcionario> funcionariosList = List.of(
                new Funcionario("Ana", 28, 3000.0),
                new Funcionario("Bruno", 35, 4500.0),
                new Funcionario("Carla", 22, 2800.0),
                new Funcionario("Daniel", 40, 6000.0));

        Funcionario salarioMaiorQue4000 = funcionariosList
                .stream()
                .filter(funcionario -> funcionario.getSalario() > 4000)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Não temos funcionários com valor maior que 4000"));

        System.out.println("Funcionário encontrado com valor maior que 4000: " + salarioMaiorQue4000);
    }

    @Test
    void exercicio24StreamsPreencherComOlaAntesDeCadaNome() {
        /*
            Dada a lista:
            List<Integer> numerosList = List.of(8, 1, 32, 71, 5, 12);

            Obtenha o primeiro número maior que 10
            RESPOSTA: 32
         */
        List<String> nomesList = List.of("Marcio", "Lorran", "Juvenal");
        nomesList.forEach(nome -> System.out.println("Olá " + nome));
    }

}
