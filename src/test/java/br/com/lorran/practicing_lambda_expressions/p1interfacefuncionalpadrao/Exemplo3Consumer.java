package br.com.lorran.practicing_lambda_expressions.p1interfacefuncionalpadrao;

import br.com.lorran.practicing_lambda_expressions.entities.*;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Exemplo3Consumer {

    @Test
    void exercicio1ConsumerIntrodutorioImprimirNumero() {
        // Consumer que imprime um número
        Consumer<Integer> printNumber = numero -> System.out.println("Número: " + numero);

        printNumber.accept(10); // saída: Número: 10
        printNumber.accept(25); // saída: Número: 25
    }

    @Test
    void exercicio2ConsumerEncadeado() {
        Consumer<String> print = s -> System.out.println("Normal: " + s);
        Consumer<String> upper = s -> System.out.println("Maiúsculo: " + s.toUpperCase());

        // Apenas accept()
        print.accept("java"); // 'accept' é o método principal de um consumer
        // saída: Normal: java

        // Usando andThen()
        print.andThen(upper).accept("java"); // 'andThen' serve para encadear
        // saída:
        // Normal: java
        // Maiúsculo: JAVA
    }

    @Test
    void exercicio3ConsumerComListas() {
        List<String> nomes = Arrays.asList("Ana", "Bruno", "Carla");
        Consumer<String> printNome = nome -> System.out.println("Olá " + nome);
        nomes.forEach(printNome); // basicamente, foreach recebe um Consumer, e 'printNome' é um Consumer
    }

    @Test
    void exercicio4ConsumerImprimeNumeroAndImprimeSeNumeroParOuImpar() {
        // EXERCÍCIO COM 'accept'
//        Consumer<Integer> parOuImpar = n -> {
//            if (n % 2 == 0) {
//                System.out.println(n + " é par");
//            } else {
//                System.out.println(n + " é ímpar");
//            }
//        };

        Consumer<Integer> parOuImpar = numero -> {
            String resultado = numero % 2 == 0 ? " é par" : " é ímpar";
            System.out.println(numero + resultado);
        };

        parOuImpar.accept(3); // 3 é ímpar
        parOuImpar.accept(10); // 10 é par
    }

    @Test
    void exercicio5ConsumerPrimeiroImprimeNumeroDepoisRaizQuadradaDele(){
        // EXERCÍCIO COM 'andThen'
        Consumer<Integer> printNumber = n -> System.out.println("Número: " + n);
        Consumer<Integer> printSquare = n -> System.out.println("Quadrado: " + (n * n));

        Consumer<Integer> encadeado = printNumber.andThen(printSquare);

        encadeado.accept(5);
        encadeado.accept(9);
        // vemos que as ações são de baixo para cima
    }

    @Test
    void exercicio6ConsumerImprimirTextoOriginalAndMaiusculoAndTamanhoString(){
        // EXERCÍCIO MISTO, OU SEJA, COM 'accept' e 'andThen'
        List<String> palavras = Arrays.asList("Java", "Consumer", "Lambda");

        Consumer<String> original = s -> System.out.println("Original: " + s);
        Consumer<String> upper = s -> System.out.println("Maiúsculo: " + s.toUpperCase());
        Consumer<String> tamanho = s -> System.out.println("Tamanho: " + s.length());

        Consumer<String> pipeline = original.andThen(upper).andThen(tamanho);

        palavras.forEach(pipeline); // o 'accept' está dentro desse 'forEach', entre no método e verá
    }

    @Test
    void exercicio7ConsumerMostrarValorOriginalAndSeuValorFormatadoEmReais(){
        // EXERCÍCIO MISTO TAMBÉM, ENVOLVENDO FORMATAÇÃO
        List<Double> precos = Arrays.asList(10.0, 25.5, 100.99);

        Consumer<Double> mostrarOriginal = v -> System.out.println("Valor: " + v);
        Consumer<Double> mostrarReais = v -> {
            String formatado = NumberFormat.getCurrencyInstance().format(v);
            System.out.println("Formatado: " + formatado);
        };

        Consumer<Double> pipeline = mostrarOriginal.andThen(mostrarReais);
        precos.forEach(pipeline);
    }

    @Test
    void exercicio8ConsumerMostrarTextoFormatadoDeNomeAndQtdLetrasNoNome(){
        // MOSTRAR A SEGUINTE FORMATAÇÃO: "Olá, [nome]" e "Nome tem [X] letras".
        List<String> nomes = Arrays.asList("Ana", "Bruno", "Carla");

        Consumer<String> saudacao = nome -> System.out.println("Olá, " + nome);
        Consumer<String> tamanho = nome -> System.out.println(nome + " tem " + nome.length() + " letras");

        Consumer<String> pipeline = saudacao.andThen(tamanho);
        nomes.forEach(pipeline);
    }

    // --------------------------------------------------------------------------------|

    @Test
    void exercicio1ComObjetoConsumerRecebeAlunoAndImprimeNomeAndNotaAndSeEstahAprovado(){
        // Critério para estar aprovado: nota >= 7

        List<Aluno> alunos = Arrays.asList(
                new Aluno("Ana", 8.5),
                new Aluno("Bruno", 6.0)
        );

        Consumer<Aluno> avaliar = a -> {
            System.out.println("Aluno: " + a.getNome());
            System.out.println("Nota: " + a.getNota());
            System.out.println(a.getNota() >= 7 ? "Aprovado" : "Reprovado");
            System.out.println("-----");
        };

        alunos.forEach(avaliar);
    }

    @Test
    void exercicio2ComObjetoConsumerImprimeNomeProfessorAndMateriaQueEleEnsina(){
        // USO DO 'andThen' para trabalhar com o objeto 'Professor'

        List<Professor> profs = Arrays.asList(
                new Professor("Marcos", "Matemática"),
                new Professor("Paula", "História")
        );

        Consumer<Professor> printNome = p -> System.out.println("Professor: " + p.getNome());
        Consumer<Professor> printMateria = p -> System.out.println("Ensina: " + p.getMateria());

        Consumer<Professor> pipeline = printNome.andThen(printMateria);

        profs.forEach(pipeline);
    }

    @Test
    void exercicio3ComObjetoConsumerMarcaSalaComoLimpaAndImprimeTexto(){
        // AQUI, A IDÉIA É ALTERAR O ESTADO DO OBJETO DE 'false' PARA 'true' NO ATRIBUTO 'isLimpa'
        /*
            As ações serão as seguintes:
                - Marca a sala como “limpa”
                - Imprime: "Sala X foi limpa."

            COM ISSO, APRENDEMOS QUE O CONSUMER PODE ALTERAR O ESTADO DO OBJETO
         */

        List<Sala> salas = Arrays.asList(
                new Sala("101"),
                new Sala("102")
        );

        Consumer<Sala> limparSala = s -> {
            s.limpar();
            System.out.println("Sala " + s.getNome() + " foi limpa.");
        };

        salas.forEach(limparSala);
    }

    // CONTINUAR DO 'EXERCÍCIO 4' de cima para baixo na conversa 'Usando o Consumer em Java'

    @Test
    void exercicio4ComObjetoConsumerImprimeNomeAndDiasAndMaisDe7DiasDescontoAplicado(){
        // A IDÉIA É USAR O CONSUMER COM VALIDAÇÃO
        /*
            Crie um Consumer que recebe um Hospede e:
            - imprime o nome
            - imprime a quantidade de dias
            - se ficar mais de 7 dias, imprime "Desconto aplicado!"
         */

        List<Hospede> hospedes = Arrays.asList(
                new Hospede("Carlos", 3, "000.000.000-00"),
                new Hospede("Fernanda", 10, "000.000.000-00")
        );

        Consumer<Hospede> analisar = h -> {
            System.out.println("Hóspede: " + h.getNome());
            System.out.println("Dias: " + h.getDias());
            if (h.getDias() > 7) {
                System.out.println("Desconto aplicado!");
            }
            System.out.println("-----");
        };

        hospedes.forEach(analisar);
    }

    @Test
    void exercicio5ComObjetoConsumerAplicaAumentode500AoFuncionarioDepoisImprimeAtual(){
        // A IDÉIA É CHAMAR UM MÉTODO QUE ESTÁ DENTRO DE OUTRA CLASSE E SEGUIR TRABALHANDO
        /*
            Crie dois Consumers:
            - um que aplica um aumento de R$ 500 ao funcionário
            - outro que imprime o nome e salário atualizado
            - depois encadeie com andThen.
         */

        List<Funcionario> funcionarios = Arrays.asList(
                new Funcionario("João", 25, 2500.0),
                new Funcionario("Marina", 26,3200.0)
        );

        Consumer<Funcionario> aumentar = f -> f.aumentar(500);
        Consumer<Funcionario> printInfo = f -> System.out.println(
                f.getNome() + " agora ganha " + f.getSalario()
        );

        Consumer<Funcionario> pipeline = aumentar.andThen(printInfo);

        funcionarios.forEach(pipeline);
    }
}
