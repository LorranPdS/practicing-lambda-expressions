package br.com.lorran.practicing_lambda_expressions.p1interfacefuncionalpadrao;

import br.com.lorran.practicing_lambda_expressions.entities.AlunoEntity;
import br.com.lorran.practicing_lambda_expressions.entities.PersonEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class Exemplo1Predicate {

    /*
        Caso você tente imprimir algo como o que está abaixo, não irá funcionar, imprimindo apenas o endereço de memória

        ------
        Predicate<String> iniciaLetraB = l -> l.startsWith("B");
        System.out.println(iniciaLetraB);
        ------

        Isso é esperado, porque você está imprimindo o objeto Predicate em si, e não o resultado da função lambda que ele representa.
        Você precisa chamar o metodo 'test()' da interface Predicate
     */

    @Test
    @DisplayName("Usando o String como pré-requisito e fazendo a verificação")
    void exercicio1Predicate() {
        Predicate<String> iniciaLetraBPredicate = l -> l.startsWith("B"); // representação do Predicate

        System.out.println(iniciaLetraBPredicate.test("Thaís")); // uso do predicate para testar o conteúdo
        System.out.println(iniciaLetraBPredicate.test("Bianca")); // uso do predicate para testar o conteúdo
    }

    /*
        Aqui eu estou usando um exemplo que pode ser usado com String e também com Integer, fazendo ainda o uso
        de um tipo genérico em um método, colocando também o Predicate por parâmetro para vermos que é possível.

     */

    @Test
    @DisplayName("Usando o String e o Integer, depois vendo o Predicate sendo passado por parâmetro")
    void exercicio2Predicate() {
        Predicate<String> comecaComAPredicate = s -> s.startsWith("A");
        Predicate<Integer> ehParPredicate = n -> n % 2 == 0;

        testarPredicate("Começa com A", comecaComAPredicate, "Ana");
        testarPredicate("Começa com A", comecaComAPredicate, "Bruno");

        testarPredicate("É par", ehParPredicate, 10);
        testarPredicate("É par", ehParPredicate, 7);
    }

    /*
        Neste exemplo, eu irei pegar um dos atributos do objeto para
        fazer a verificação
     */

    @Test
    @DisplayName("Usando objetos para usar o Predicate - ver se pessoa é maior de idade")
    void exercicio3Predicate() {
        PersonEntity maria = new PersonEntity("Maria", 25);
        PersonEntity betania = new PersonEntity("Betânia", 17);

        Predicate<PersonEntity> ehMaiorDeIdadePredicate = i -> i.getAge() >= 18;

        testarPredicate("É maior de idade", ehMaiorDeIdadePredicate, maria);
        testarPredicate("É maior de idade", ehMaiorDeIdadePredicate, betania);
    }

    @Test
    @DisplayName("Usando objetos para usar o Predicate - filtragem de listas e usando filter")
    void exercicio4Predicate() {
        List<PersonEntity> pessoas = List.of(
                new PersonEntity("Joana", 17),
                new PersonEntity("Mirela", 22),
                new PersonEntity("Fabi", 15),
                new PersonEntity("Ana", 30)
        );

        Predicate<PersonEntity> maiorDeIdadePredicate = p -> p.getAge() >= 18;

        List<PersonEntity> adultos = pessoas.stream()
                .filter(maiorDeIdadePredicate) // você consegue ver que o 'filter' usa um Predicate por parâmetro. Entre no 'filter' para ver
                .toList();

        /*
            List<PersonEntity> adultos = pessoas.stream()
                .filter(p -> p.getAge() >= 18) // você consegue ver que o 'filter' usa um Predicate por parâmetro. Entre no 'filter' para ver
                .toList();
         */
        adultos.forEach(System.out::println);
    }


    private <T> void testarPredicate(String descricao, Predicate<T> predicate, T valor) {
        boolean resultado = predicate.test(valor);
        System.out.println(descricao + ": " + valor + " → " + resultado);
    }

    /*
        Os métodos como and(), or(), negate(), not() e isEqual() são métodos utilitários que
        permitem combinar ou modificar Predicates. Entender como usá-los vai turbinar sua habilidade
        com expressões lambda.
        Vamos ver exemplos simples e diretos com explicações
     */

    @Test
    @DisplayName("Uso do método utilitário 'and'")
    void exercicio5Predicate() {
        // Combina dois predicates com lógica E (&&). Só retorna true se ambos forem verdadeiros.
        Predicate<String> temMaisDe3 = s -> s.length() > 3;
        Predicate<String> comecaComA = s -> s.startsWith("A");

        Predicate<String> combinado = temMaisDe3.and(comecaComA);

        System.out.println(combinado.test("Ana"));     // false (começa com A mas tem só 3 letras)
        System.out.println(combinado.test("Amanda"));  // true
        System.out.println(combinado.test("Lucas"));   // false
    }

    @Test
    @DisplayName("Uso do método utilitário 'or'")
    void exercicio6Predicate() {
        // Combina dois predicates com lógica OU (||). Retorna true se pelo menos um for verdadeiro.
        Predicate<String> temMaisDe3 = s -> s.length() > 3;
        Predicate<String> comecaComA = s -> s.startsWith("A");

        Predicate<String> combinado = temMaisDe3.or(comecaComA);

        System.out.println(combinado.test("Ana"));     // true (começa com A)
        System.out.println(combinado.test("João"));    // true (tem mais de 3 letras)
        System.out.println(combinado.test("Lu"));      // false
    }

    @Test
    @DisplayName("Uso do método utilitário 'negate'")
    void exercicio7Predicate() {
        // Inverte o resultado lógico (como um !).
        Predicate<String> comecaComA = s -> s.startsWith("A");
        Predicate<String> naoComecaComA = comecaComA.negate();

        System.out.println(naoComecaComA.test("Ana"));   // false
        System.out.println(naoComecaComA.test("Bruno")); // true
    }

    @Test
    @DisplayName("Uso do método estático 'isEqual'")
    void exercicio8Predicate() {
        // Retorna um Predicate que verifica se o valor é igual ao objeto x usando .equals().
        // OBSERVAÇÃO: cuidado com null, pois isEqual(null) só retorna true se o valor testado também for null.
        Predicate<String> igualAJava = Predicate.isEqual("Java");

        System.out.println(igualAJava.test("Java"));    // true
        System.out.println(igualAJava.test("Python"));  // false
    }

    @Test
    @DisplayName("Uso do método estático 'not'")
    void exercicio9Predicate() {
        // Faz o mesmo que negate(), mas de forma mais legível. Ideal para usar com method references.
        List<String> nomes = List.of("Ana", "Bruno", "Carlos", "Amanda");

        List<String> naoComecamComA = nomes.stream()
                .filter(Predicate.not(s -> s.startsWith("A")))
                .collect(Collectors.toList());

        System.out.println(naoComecamComA); // [Bruno, Carlos]
    }

    @Test
    @DisplayName("Combinando tudo que foi aprendido em Predicate")
    void exercicio10Predicate() {
        Predicate<String> p1 = s -> s.equals("Java");
        Predicate<String> p2 = s -> s.length() > 3;
        Predicate<String> p3 = s -> !s.contains("x");

        Predicate<String> resultado = p1.or(p2).and(p3).negate();

        System.out.println(resultado.test("Java"));    // false
        System.out.println(resultado.test("Javascript")); // false
        System.out.println(resultado.test("Lua"));     // true
    }

    /*
        Vamos montar um exemplo completo usando Predicate<AlunoEntity> com uma
        lógica mais realista — sem depender de String ou Integer diretamente.
        A ideia é verificar se o aluno:
        - tem média maior ou igual a 7.0
        - e também tem menos de 5 faltas
     */

    @Test
    @DisplayName("Exemplo de predicate com mais de um método do objeto")
    void exercicio11Predicate() {
        List<AlunoEntity> alunos = List.of(
                new AlunoEntity("Ana", 8.5, 2),
                new AlunoEntity("Bruno", 6.8, 3),
                new AlunoEntity("Carla", 7.2, 6),
                new AlunoEntity("Daniel", 9.0, 1),
                new AlunoEntity("Eduarda", 7.5, 4)
        );

        // Condição: aprovado se média >= 7 e faltas < 5
        Predicate<AlunoEntity> aprovado = a -> a.getMedia() >= 7.0 && a.getFaltas() < 5;

        List<AlunoEntity> aprovados = alunos.stream()
                .filter(aprovado)
                .toList();

        System.out.println("Alunos aprovados:");
        aprovados.forEach(System.out::println);

        // Carla tem média suficiente mas muitas faltas
        // Bruno tem poucas faltas mas média insuficiente
    }

}
