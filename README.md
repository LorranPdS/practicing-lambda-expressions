# practicing-lambda-expressions

# **O QUE SÃO EXPRESSÕES LAMBDA?**

Uma expressão lambda é uma forma concisa de representar uma função anônima, ou seja, uma função que não precisa ter um nome, mas que pode ser passada como argumento ou atribuída a uma variável.

Ela tem a seguinte forma básica:

`(parâmetros) -> expressão ou bloco de código`

# **REQUISITOS PARA APRENDER LAMBDA**

Antes de mergulhar nas lambdas, é importante ter uma boa base em:

a) Interfaces Funcionais (como Runnable, Comparator<T>, Predicate<T>, etc.)

b) Programação orientada a objetos

c) Conceito de classes anônimas

d) Conhecimento básico de Streams API (para aplicar lambdas em coleções)


# **ETAPAS PARA APRENDER LAMBDA BEM**

As etapas são basicamente as seguintes:

a) entender Interfaces Funcionais - as lambdas só funcionam onde há interfaces com um único método abstrato.

Exemplo:

`@FunctionalInterface
interface Operacao {
    int executar(int a, int b);
}`

Lambda:

`Operacao soma = (a, b) -> a + b;`

`System.out.println(soma.executar(3, 5)); // 8`

b) praticar lambdas com APIs conhecidas - vejamos alguns exemplos úteis:

`List<String> nomes = Arrays.asList("João", "Ana", "Carlos");`

// Ordenar com lambda

`nomes.sort((a, b) -> a.compareTo(b));`

`nomes.forEach(nome -> System.out.println(nome));`

c) aprender sobre a Stream API - a API de streams permite aplicar lambdas de forma funcional:

`List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);`

// Filtrar pares e dobrar os valores

`List<Integer> paresDobro = numeros.stream()
.filter(n -> n % 2 == 0)
.map(n -> n * 2)
.collect(Collectors.toList());`

d) experimentar tipos de interfaces funcionais padrão - essas estão no pacote java.util.function:
- Predicate<T> → retorna booleano (filter)
- Function<T, R> → transforma um valor (map)
- Consumer<T> → consome um valor (forEach)
- Supplier<T> → fornece um valor (get)

----

## INTERFACES FUNCIONAIS PADRÃO – Início de tudo

Essas interfaces têm nomes padronizados e são amplamente utilizadas em conjunto com lambdas e a API de Streams. Aqui vão as principais:

1. Predicate<T>

A palavra "predicate" vem da lógica matemática e lógica de predicados.

Na prática, um predicado é **uma função que testa se algo é verdadeiro ou falso**.

Formalmente:

`Predicate<T> é uma função: T → boolean`

Ou seja, ela recebe um objeto do tipo T e devolve true ou false, conforme alguma condição lógica.

Resumindo:

- função: testa uma condição e retorna true ou false
- método principal: boolean test(T t)
- usos: filter() em streams, validações

----

2. Function<T, R>

A interface Function<T, R> faz parte do pacote `java.util.function` e representa uma função que recebe um argumento do tipo T e retorna um resultado do tipo R.

O nome "Function" é curto, direto e consistente com a ideia que representa: uma função pura, que recebe um valor e retorna outro.

É uma interface funcional genérica, pensada para representar qualquer transformação de um valor em outro.

Esta é a assinatura da Function:

`@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
`

- função: transforma um tipo em outro
- método principal: R apply(T t)
- usos: map() em streams

Ela é útil quando você precisa transformar um valor de um tipo em outro. Exemplos:

- converter um objeto em outro tipo (ex: String → Integer)
- mapear elementos em streams
- aplicar funções genéricas de transformação

----

3. Consumer<T>
- função: consome um valor e realiza uma ação (sem retorno)
- método principal: void accept(T t)
- usos: forEach()

4. Supplier<T>
- função: fornece um valor, sem receber nenhum argumento
- método principal: T get()
- usos: gerar valores (UUIDs, datas, números aleatórios)
