package br.com.lorran.practicing_lambda_expressions.p4exgeralinterfacesfuncionais;

import br.com.lorran.practicing_lambda_expressions.entities.Funcionario;
import br.com.lorran.practicing_lambda_expressions.entities.Pessoa;
import br.com.lorran.practicing_lambda_expressions.entities.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;

public class Example1UsingBiFunctionsTest {

    // BiFunctions são usadas em Streams no 'reduce()' ou mesmo em 'Collectors' como o 'Collectors.toMap()' e o 'Collectors.groupingBy()'
    @Test
    void exercicio1BiFunctionSomarDoisNumerosESomarMais10(){
        // Crie um BiFunction<Integer, Integer, Integer> que some dois números e adicione 10 ao resultado final.

        BiFunction<Integer, Integer, Integer> somaComBonus = (a, b) -> a + b + 10;
        int resultado = somaComBonus.apply(2, 3);
        System.out.println(resultado);
    }

    @Test
    void exercicio2BiFunctionPegarMaiorValorDeDoisNumeros(){
        // Crie um BiFunction<Integer, Integer, Integer> que retorne o maior valor entre dois números.

        BiFunction<Integer, Integer, Integer> maiorValorBiFunction = (valor1, valor2) -> valor1 > valor2 ? valor1 : valor2;
        int maiorValorEntre2Numeros = maiorValorBiFunction.apply(8, 6);
        System.out.println(maiorValorEntre2Numeros);
    }

    @Test
    void exercicio3BiFunctionConcateneNomeAndSobrenome(){
        // Informe nome e sobrenome de usuário e retorne uma concatenação em que exiba diretamente o nome e o sobrenome concatenados.

        BiFunction<String, String, String> nomeConcatenaSobrenomeBiFunction = (nome, sobrenome) -> nome +" "+ sobrenome;
        String nomeAndSobrenome = nomeConcatenaSobrenomeBiFunction.apply("Lorran", "Santos");
        System.out.println(nomeAndSobrenome);
    }

    @Test
    void exercicio4BiFunctionCriePessoaInformandoNomeAndIdade(){
        // Crie uma nova Pessoa informando nome e idade

//        BiFunction<String, Integer, Pessoa> pessoaBiFunction = (nome, idade) -> new Pessoa(nome, idade); // abordagem com lambda expression
        BiFunction<String, Integer, Pessoa> pessoaBiFunction = Pessoa::new; // abordagem com Method Reference
        Pessoa pessoa = pessoaBiFunction.apply("Lorran", 34);
        System.out.println(pessoa);
    }

    @Test
    void exercicio5BiFunctionCrieNovoProdutoInformandoNomeAndPreco(){
        // Crie um novo Produto informando nome e preço

//        BiFunction<String, Double, Produto> produtoBiFunction = (nome, preco) -> new Produto(nome, preco); // // abordagem com lambda expression
        BiFunction<String, Double, Produto> produtoBiFunction = Produto::new;
        Produto produto = produtoBiFunction.apply("Café", 50.0);
        System.out.println(produto);
    }

    @Test
    void exercicio6BiFunctionAumentarSalarioDeFuncionario(){
        /*
             Aumentar salário do funcionário Luiz >>> new Funcionario("Luiz", 28, 2200.0);
             Obs.: já existe o metodo "aumentar(double)" em 'Funcionario' para fazer o cálculo de aumento de salário
         */
        Funcionario funcionario = new Funcionario("Luiz", 28, 2200.0);
        System.out.println("Salário atual do funcionário " + funcionario.getNome() + " é de " + funcionario.getSalario());
        /*
            Caso não existisse o metodo 'aumentar(double)' na entidade, poderia ser feito assim:

            BiFunction<Funcionario, Double, Funcionario> funcionarioAumentarSalarioBiFunction =
                (funcionarioParaReceberAumento, aumento) -> {
                    funcionarioParaReceberAumento.setSalario(funcionarioParaReceberAumento.getSalario() + aumento);
                    return funcionarioParaReceberAumento;
                };
         */

        BiFunction<Funcionario, Double, Funcionario> funcionarioAumentarSalarioBiFunction =
                (funcionarioParaReceberAumento, aumento) -> {
                    funcionarioParaReceberAumento.aumentar(aumento);
                    return funcionarioParaReceberAumento;
                };

        Funcionario funcionarioSalarioAumentado = funcionarioAumentarSalarioBiFunction.apply(funcionario, 300.0);
        System.out.println("Salário do funcionário " + funcionarioSalarioAumentado.getNome() + " aumentou para " + funcionarioSalarioAumentado.getSalario());
    }

    @Test
    void exercicio7BiFunctionAplicarAumento10PorcentoNoPrecoDoProduto(){
        // Aplicar imposto de 10% no preço do produto.
        Produto produtoSemImposto = new Produto("Café", 40.0);
        System.out.println("PRODUTO SEM OS 10% DE IMPOSTO: " + produtoSemImposto);

        BiFunction<Produto, Double, Produto> produtoBiFunction = (produtoSendoTributado, imposto) -> {
            produtoSendoTributado.precoComImposto(imposto);
            return produtoSendoTributado;
        };

        Produto produtoCom10PorcentoDeImposto = produtoBiFunction.apply(produtoSemImposto, 0.1);
        System.out.println("PRODUTO COM IMPOSTO: " + produtoCom10PorcentoDeImposto);
    }

    @Test
    void exercicio8BiFunctionAplicarAumento10PorcentoNoPrecoDaListaDeProdutos(){
        // Aplicar imposto de 10% no preço da lista de produtos. Criei um metodo 'precoComImposto(imposto)' na entidade
        List<Produto> produtoList = List.of(
                new Produto("Arroz", 30.0),
                new Produto("Feijão", 10.0),
                new Produto("Café", 40.0));
        System.out.println("PRODUTOS SEM OS 10% DE IMPOSTO:");
        produtoList.forEach(System.out::println);

        System.out.println();

        BiFunction<List<Produto>, Double, String> produtosBiFunction = (produtosTributados, imposto) -> {
            produtosTributados.forEach(produto -> produto.precoComImposto(imposto));
            return "Produtos tributados!";
        };

        String resultado = produtosBiFunction.apply(produtoList, 0.1);
        System.out.println(resultado);

        System.out.println();
        produtoList.forEach(System.out::println);
    }
}
