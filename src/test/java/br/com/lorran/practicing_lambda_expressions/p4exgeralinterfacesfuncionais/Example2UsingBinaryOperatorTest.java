package br.com.lorran.practicing_lambda_expressions.p4exgeralinterfacesfuncionais;

import br.com.lorran.practicing_lambda_expressions.entities.Funcionario;
import br.com.lorran.practicing_lambda_expressions.entities.Pedido;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BinaryOperator;

public class Example2UsingBinaryOperatorTest {

    @Test
    void exercicio1SomarDoisValoresMonetarios(){
        // Somar dois valores monetários usando o BinaryOperator

//        BinaryOperator<Double> somarValoresBinaryOp = (valor1, valor2) -> valor1 + valor2; // usando lambda
        BinaryOperator<Double> somarValoresBinaryOp = Double::sum;

        double somaValores = somarValoresBinaryOp.apply(100.0, 50.0);
        System.out.println(somaValores);
    }

    @Test
    void exercicio2CompararDoisValoresAndPegarMaiorDeles(){
        // Informar 2 valores, comparar e deve retornar apenas o maior deles

//        BinaryOperator<Integer> maiorValorBinaryOp =
//                      (valor1, valor2) -> valor1 > valor2 ? valor1 : valor2; // usando lambda expression
        BinaryOperator<Integer> maiorValorBinaryOp = Integer::max;

        int maiorValor = maiorValorBinaryOp.apply(4, 10);
        System.out.println(maiorValor);
    }

    @Test
    void exercicio3PegarMenorNumeroInformado(){
        // Pegar menor número que for informado

        BinaryOperator<Double> menorNumeroBinaryOp = Double::min;

        double menorNumero = menorNumeroBinaryOp.apply(4.0, 1.0);
        System.out.println(menorNumero);
    }

    @Test
    void exercicio4SomarValoresInformadosBigDecimal(){
        // Somar os valores que forem informados em Bigdecimal

        BinaryOperator<BigDecimal> somarValoresInformadosBinaryOp = BigDecimal::add;

        BigDecimal somaValores = somarValoresInformadosBinaryOp.apply(BigDecimal.valueOf(8), BigDecimal.TEN);
        System.out.println(somaValores);
    }

    // ----------------------------------Uso de Binary Operator em Streams ---------------------------------------------

    /*
        Regra mental antes de começar o exercício
        Sempre que pensar em BinaryOperator + Stream, leia assim:
        - “Comparar dois elementos do próprio stream.”
     */

    @Test
    void exercicio5SomarListaDeNumerosUsandoReduce(){
        // Somar todos os números que estão em uma lista e retornar o resultado final da soma deles
        List<Integer> numeros = List.of(10, 20, 30);
        BinaryOperator<Integer> somarNumerosBinaryOp = Integer::sum; // posso passar esse reference no reduce direto também

//        int somaItensDaLista = numeros.stream().reduce(somarNumerosBinaryOp).orElse(0); // pode ser escrito assim também
        int somaItensDaLista = numeros.stream().reduce(0, somarNumerosBinaryOp);
        System.out.println(somaItensDaLista);
    }

    @Test
    void exercicio6SomarSalarioDosFuncionarios(){
        // Calcular o total da folha salarial

        List<Funcionario> funcionarios = List.of(
                new Funcionario("João", 21, 3000.0),
                new Funcionario("Maria", 25,4000.0),
                new Funcionario("Pedro", 30, 3500.0));

        BinaryOperator<Double> somaSalarioFuncionariosBinaryOp = Double::sum;

        double totalFolhaSalarial = funcionarios
                .stream()
                .map(Funcionario::getSalario)
                .reduce(0.0, somaSalarioFuncionariosBinaryOp);

        System.out.println(totalFolhaSalarial);
    }

    @Test
    void exercicio7SomarValoresDosPedidos(){
        // Somar valores dos pedidos

        List<Pedido> pedidos = List.of(
                new Pedido(1, 50.0),
                new Pedido(2, 30.0),
                new Pedido(3, 20.0));

        /*
        // PODE SER FEITO DIRETO ASSIM
        double somaValorPedidos = pedidos.stream()
                .map(Pedido::getValor)
                .reduce(0.0, Double::sum);
         */

        BinaryOperator<Double> somaTotalPedidosBinaryOp = Double::sum;

        double somaValorPedidos = pedidos.stream()
                        .map(Pedido::getValor)
                        .reduce(0.0, somaTotalPedidosBinaryOp);

        System.out.println(somaValorPedidos);
    }

    @Test
    void exercicio8ExibirMaiorNumeroDaLista(){
        // Exibir o maior número da lista
        List<Integer> numeros = List.of(10, 45, 23, 60, 5);

        BinaryOperator<Integer> maiorNumeroBinaryOp = Integer::max;
        int maiorNumero = numeros.stream()
                .reduce(maiorNumeroBinaryOp)
                .orElseThrow();

        System.out.println(maiorNumero);
    }

    @Test
    void exercicio9FuncionarioComMaiorSalario(){
        // Exibir o maior salário de uma lista de funcionários

        List<Funcionario> funcionarios = List.of(
                new Funcionario("João", 30, 3000.0),
                new Funcionario("Maria", 31, 5000.0),
                new Funcionario("Pedro", 28, 4000.0));

        /*
            Sem BinaryOperator, ficaria assim:

            Funcionario funcionarioMaiorSalario = funcionarios.stream()
                .reduce((funcionario, proximoFuncionario) -> funcionario.getSalario() > proximoFuncionario.getSalario() ? funcionario : proximoFuncionario)
                .orElseThrow();
         */

        BinaryOperator<Funcionario> funcionarioMaiorSalarioBinaryOp = (func1, proxFuncionario) -> func1.getSalario() > proxFuncionario.getSalario() ? func1 : proxFuncionario;

        Funcionario funcionarioMaiorSalario = funcionarios.stream()
                .reduce(funcionarioMaiorSalarioBinaryOp)
                .orElseThrow();

        System.out.println(funcionarioMaiorSalario);
    }
}
