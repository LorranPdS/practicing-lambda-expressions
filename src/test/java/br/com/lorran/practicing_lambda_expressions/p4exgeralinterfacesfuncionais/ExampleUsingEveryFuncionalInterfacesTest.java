package br.com.lorran.practicing_lambda_expressions.p4exgeralinterfacesfuncionais;

import br.com.lorran.practicing_lambda_expressions.entities.Funcionario;
import br.com.lorran.practicing_lambda_expressions.entities.Hospede;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ExampleUsingEveryFuncionalInterfacesTest {

    @Test // Predicate e Function
    void exercicio1ImprimirNomeDeFuncionariosSeparadosPorVirgulaComIdadeAcimaDe30(){
        /*
            Dada uma List<Funcionario>
            - verifique quais funcionários tem idade superior a 30 anos;
            - pegar apenas os nomes deles
            - colocar em uma String formatada, dessa maneira: "nome1, nome2, ..."
         */

        List<Funcionario> funcionarioList = List.of(
                new Funcionario("Duda", 31, 3.200),
                new Funcionario("Marcos", 29, 3.100),
                new Funcionario("Francy", 22, 2.000),
                new Funcionario("João", 34, 3.400));

        String nomeFuncionariosAcimaDos30 = funcionarioList.stream()
                .filter(funcionario -> funcionario.getIdade() > 30) // filtra a ação (Predicate)
                .map(Funcionario::getNome)                      // transforma (Function)
                .collect(Collectors.joining(", "));     // resolve o problema (Terminal)

        System.out.println(nomeFuncionariosAcimaDos30);
    }

    @Test // Optional, Supplier, Filter
    void exercicio2ImprimirHospedePadraoCasoNaoSejaEncontradoPorCPF(){
        /*
            Você tem registros de hóspedes na sua base de dados (simulei com uma lista), e foi feita uma requisição
            em que a busca é feita pelo CPF do hóspede.
            O problema é que o sistema está indisponível e queremos que seja exibido um hóspede temporário.

            TAREFA: caso o hóspede não seja encontrado pelo CPF, imprima o seguinte registro:
                Hospede(nome=Hóspede de Emergência, dias=1, cpf=000.000.000-00)
         */

        // A lista abaixo vai representar um registro fictício dos hóspedes no banco de dados
        List<Hospede> hospedesRegistradosBaseDeDados = List.of(
                new Hospede("João", 2, "263.992.590-37"),
                new Hospede("Gilberto", 1, "485.590.450-31"),
                new Hospede("Renan", 4, "589.659.550-68"));

        // A seguir, um cenário de busca em que o hóspede registrado não é encontrado
        String cpfHospedeSemRegistroNaBase = "360.660.030-54";
//        String cpfHospedeNaBase = "485.590.450-31";

        Hospede buscandoPorHospede = hospedesRegistradosBaseDeDados
                .stream().filter(hospede -> Objects.equals(hospede.getCpf(), cpfHospedeSemRegistroNaBase))
                .findFirst()
                .orElseGet(this.getHospedeDeEmergencia());

        System.out.println(buscandoPorHospede);
    }

    private Supplier<Hospede> getHospedeDeEmergencia(){
        return () -> new Hospede("Hóspede de Emergência", 1, "000.000.000-00");
    }

    @Test // Optional, Supplier, Filter
    void exercicio3LancarExcecaoCasoHospedeNaoSejaEncontradoPorCPF(){
        /*
            Você tem registros de hóspedes na sua base de dados (simulei com uma lista), e foi feita uma requisição
            em que a busca é feita pelo CPF do hóspede.
            Porém, será feita uma busca por um cliente que não existe na base de dados

            TAREFA: caso o hóspede não seja encontrado pelo CPF, lance uma exceção informando que o hóspede não foi encontrado
         */

        // A lista abaixo vai representar um registro fictício dos hóspedes no banco de dados
        List<Hospede> hospedesRegistradosBaseDeDados = List.of(
                new Hospede("João", 2, "263.992.590-37"),
                new Hospede("Gilberto", 1, "485.590.450-31"),
                new Hospede("Renan", 4, "589.659.550-68"));

        // A seguir, um cenário de busca em que o hóspede registrado não é encontrado
        String cpfHospedeSemRegistroNaBase = "360.660.030-54";
//        String cpfHospedeNaBase = "485.590.450-31";

        // MEU FOCO NO EXERCÍCIO: trabalhar com STREAMS
        Assertions.assertThrows(RuntimeException.class, () ->
            hospedesRegistradosBaseDeDados
                    .stream().filter(hospede -> Objects.equals(hospede.getCpf(), cpfHospedeSemRegistroNaBase))
                    .findFirst()
                    .orElseThrow(getSistemaSeEncontraIndisponivel()));
    }

    // posso colocar o conteúdo do método direto na chamada dele, mas deixei assim para eu entender as possibilidades do Supplier na assinatura
    private static Supplier<RuntimeException> getSistemaSeEncontraIndisponivel() {
        return () -> new RuntimeException("Hóspede não foi encontrado");
    }

    @Test // Optional, Supplier, Filter, Function
    void exercicio4BuscaFlexivelPorCPFIndependenteSeHaOuNaoFormatacao(){
        // A lista abaixo vai representar um registro fictício dos hóspedes no banco de dados
        List<Hospede> hospedesRegistradosBaseDeDados = List.of(
                new Hospede("Lucio", 1, "263.992.590-37"),
                new Hospede("Flavio", 3, "485.590.450-31"),
                new Hospede("Tatiane", 2, "589.659.550-68"));

        String cpfTatianeSemFormatacao = "58965955068";

        /*
            1. AÇÃO: Função que "limpa" qualquer String de CPF
                Use o método .replaceAll("\\D", "").
                O código regex \\D seleciona qualquer caractere que não seja um dígito (0-9) e o substitui por nada.
         */
        Function<String, String> limparCpfRegex = cpf -> cpf.replaceAll("\\D", "");

        // 2. RESOLUÇÃO: Aplicamos a limpeza nos dois lados da comparação
        Hospede buscandoPorHospede = hospedesRegistradosBaseDeDados.stream()
                .filter(h -> limparCpfRegex.apply(h.getCpf()).equals(limparCpfRegex.apply(cpfTatianeSemFormatacao)))
                .findFirst()
                .orElseGet(this.getHospedeDeEmergencia());

        System.out.println(buscandoPorHospede);

        /*
            Dica extra: Se você estiver usando Spring Boot em seus projetos, você pode
            usar a anotação @CPF do Hibernate Validator para garantir que o dado entre sempre no
            formato correto em suas APIs.
         */
    }

}
