package br.com.lorran.practicing_lambda_expressions.p1interfacefuncionalpadrao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

// LEMBRANDO QUE A TEORIA ESTÁ DESCRITA NO README.md
@ExtendWith(MockitoExtension.class)
public class Exemplo4Supplier {

    @Test
    void exercicio1SupplierINTRODUTORIORetornaString() {
        // Só fazer o Supplier retornar uma String
        // Deve retornar: Olá, sou um Supplier!
        Supplier<String> mensagem = () -> "Olá, sou um Supplier!";
        System.out.println(mensagem.get());
    }

    @Test
    void exercicio2SupplierINTRODUTORIOCriarListaVaziaDeString() {
        // Exibir uma lista vazia de String
        // Deve retornar: []

        /*
        Por que dessa forma aqui não compila?
        Supplier<List<String>> listaVaziaSupplier = () -> ArrayList::new;

        RESPOSTA:
        Porque do jeito certo (que está no exercício) está sendo usado o "Method Reference"
        como construtor.

        ArrayList::new significa: “Quando alguém chamar get(), execute new ArrayList<>() e retorne esse ArrayList.”

        ------------------------------------------------------------------------

        Aqui você está criando uma lambda que retorna uma expressão (ArrayList::new), e não uma instância de ArrayList.
        Ou seja, a lambda está tentando retornar uma referência a um método, não o objeto em si.

        Tradução disso:
            ArrayList::new não é um ArrayList, é um constructor reference, mas o Supplier espera um List, não uma referência de método

            Equivalente ao erro:

            List<String> lista = ArrayList::new; // isso é inválido também!

            O Supplier<T> sempre deve retornar um valor concreto do tipo T, e nunca uma "função", um método.

        Funcionaria se estivesse da forma abaixo:
            Supplier<List<String>> listaVaziaSupplier = () -> new ArrayList<>();

        PORTANTO:
        - O Supplier fornece um valor pronto.
        - Ele não fornece instruções sobre como criar o valor (isso seria um method reference ou outra função).
        - O método get() precisa retornar um objeto, não uma função.

         */
        Supplier<List<String>> listaVazia = ArrayList::new;
        List<String> lista = listaVazia.get();
        System.out.println(lista);
    }

    @Test
    void exercicio3SupplierINTRODUTORIOGerandoNumerosAleatorios() {
        // Fazer o Supplier retornar uma numeração aleatória
        // Deve retornar: qualquer número entre 0 e 10
        Supplier<Integer> random = () -> new Random().nextInt(10);
        System.out.println(random.get()); // IMPORTANTE: lembre-se de acessar o conteúdo com o método ".get()"
    }

    // ---------------------------------- EXERCÍCIOS ABAIXO----------------------------------------------------------------------------------------------------------------------

    @Test
    void exercicio1SupplierRetornarMeuNomeEmString(){
        Supplier<String> meuNomeSupplier = () -> "Lorran";
        System.out.println(meuNomeSupplier.get());
    }

    @Test
    void exercicio2SupplierRetornarDataHoraAtual(){
        Supplier<LocalDateTime> dataHoraAtualSupplier = LocalDateTime::now;
        System.out.println(dataHoraAtualSupplier.get());
    }

    @Test
    void exercicio3SupplierRetornarNumeroAleatorioDe1Ate100(){
        Supplier<Integer> numeroAleatorioSupplier = () -> new Random().nextInt(100) + 1;
        System.out.println(numeroAleatorioSupplier.get());
    }

    @Test
    void exercicio4SupplierRetornarUmaListaJaPreenchidaCom3Nomes(){
        // Crie um Supplier<List<String>> que retorne uma lista já preenchida com 3 nomes.
        Supplier<List<String>> listaNomes = () -> Arrays.asList("Adam", "Davi", "Yitzchak");
        System.out.println(listaNomes.get());
    }

    @Test
    void exercicio5SupplierRetornarUmaListaJaPreenchidaCom3Nomes(){

        /*
        Você tem uma função que recebe um Supplier e o executa:

            public static <T> void executar(Supplier<T> supplier) {
                System.out.println("Valor fornecido: " + supplier.get());
            }

        Use essa função para imprimir:
            - um número aleatório
            - uma saudação
            - a data atual
         */

        /*
            RESPOSTA QUE EU TINHA FEITO:

                Supplier<Integer> valorAleatorioSupplier = () -> new Random().nextInt(34);
                Supplier<String> saudacaoSupplier = () -> "Boa tarde!";
                Supplier<LocalDate> dataAtualSupplier = LocalDate::now;

                List<Supplier<?>> meusSuppliers = Arrays.asList(valorAleatorioSupplier, saudacaoSupplier, dataAtualSupplier);
                meusSuppliers.forEach(Exemplo4Supplier::executar);
         */

        // RESPOSTA MAIS "DIRETO AO PONTO"
        executar(() -> new Random().nextInt(100));    // número aleatório
        executar(() -> "Olá, mundo!");                // saudação
        executar(LocalDate::now);              // data atual
    }

    public static <T> void executar(Supplier<T> supplier) {
        System.out.println("Valor fornecido: " + supplier.get());
    }

    // ---------------------------------- EXERCÍCIOS AVANÇADOS ABAIXO----------------------------------------------------------------------------------------------------------------------

    @Test
    void exercicio1SupplierContratacaoFuncionarios(){
        // ESTUDAR EXERCÍCIOS CHATGPT DO TÍTULO "Entendendo o Supplier" DA PARTE "Fábrica dinâmica de funcionários baseada em política de contratação"
    }

}
