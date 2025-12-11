package br.com.lorran.practicing_lambda_expressions.p1interfacefuncionalpadrao;

import br.com.lorran.practicing_lambda_expressions.entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
        /*
            1 — Fábrica dinâmica de funcionários baseada em política de contratação (RH Estratégico)
                Enunciado:

                Sua empresa possui várias políticas de contratação (júnior, pleno, sênior).
                Cada política define salário, benefícios e carga horária.

                O que fazer:
                Crie um Supplier<Funcionario> que, ao ser executado, consulte a política atual (que pode mudar em runtime) e crie o funcionário de acordo com ela.
                A política deve ser um objeto separado.

                -----------------
                Saída:
                Funcionario(nome=Marcelo, idade=25, salario=5200.0)
                PoliticaContratacao(nivel=Pleno, salarioBase=5200.0, horasSemana=40)

                "IDEIA CHAVE: Supplier como delegador de regras de negócio flexíveis."
         */

        PoliticaContratacao politicaAtual = new PoliticaContratacao("Pleno", 5200, 40);
        Supplier<Funcionario> fabricaFuncionario = () ->
                politicaAtual.criarFuncionario("Marcelo");

        Funcionario funcionario = fabricaFuncionario.get();  // usará a nova política!
        System.out.println(funcionario);
        System.out.println(politicaAtual);
    }

    @Test
    void exercicio2SupplierGeradorProdutoCadeiaLogistica(){
        /*
            2 — Gerador de Produto que usa Supplier para simular cadeia logística
            Enunciado:

            Simule o fluxo:
            - sistema gera o pedido
            - fornecedor gera o produto
            - centro de distribuição consolida
            - supplier final retorna Produto pronto para envio

            O que fazer:
            Implemente usando um Supplier que depende de outros Suppliers internos.

            -----------------
            Saída:
            Consolidado no centro pela Fornecedor A
            Produto(nome=Cadeira Ergonômica (Embalada), preco=799.9)

            IDEIA CHAVE: Supplier como etapas de pipeline.
         */
        Supplier<String> fornecedorSupplier = () -> "Fornecedor A";
        Supplier<Produto> producaoSupplier = () ->
                new Produto("Cadeira Ergonômica", 799.90);

        Supplier<Produto> logistica = () -> {
            Produto p = producaoSupplier.get();
            System.out.println("Consolidado no centro pela " + fornecedorSupplier.get());
            return new Produto(p.getNome() + " (Embalada)", p.getPreco());
        };

        Produto finalizado = logistica.get();
        System.out.println(finalizado);
    }

    @Test
    void exercicio3SupplierPlanoAulaInteligenteParaProfessor(){
        /*
            3 — Plano de Aula Inteligente para Professor (Supplier como motor de recomendação)
            Enunciado:

            Crie um Supplier de Professor que ao ser chamado:
            - gera um professor
            - monta automaticamente um plano de aula baseado na disciplina
            - o plano vem de um Map<disciplina, List<conteudos>>

            IDEIA CHAVE: Supplier como motor de recomendação contextual.
         */
        Map<String, List<String>> planosMap = new HashMap<>();
        planosMap.put("Matemática", List.of("Funções", "Trigonometria", "Logaritmos"));
        planosMap.put("POO", List.of("Classes", "Métodos", "Encapsulamento"));

        Supplier<Professor> professorStrategicoSupplier = () -> {
            String materia = "POO";
            List<String> conteudos = planosMap.get(materia);

            Professor p = new Professor("Roger", materia);
            System.out.println("Matéria: " + materia + " >>>>>>> Plano sugerido: " + conteudos);
            return p;
        };

        Professor prof = professorStrategicoSupplier.get();
        System.out.println(prof);
    }

    @Test
    void exercicio4SupplierLimpezaAndAgendamentoAndOcupacaoDeSala(){
        /*
            4 — Supplier<Sala> com simulação operacional (limpeza, agendamento e ocupação)
            Enunciado:

            Crie um Supplier de Sala que:
            - verifica se a sala está limpa
            - se não estiver, gera “tarefas de limpeza”
            - agenda o próximo horário disponível
            - retorna a sala pronta para uso

            Ideia chave: Supplier como orquestrador de operações.
         */

        Supplier<Sala> salaOperacionalSupplier = () -> {
            Sala sala = new Sala("Sala 12");

            if(!sala.isLimpa()) {
                System.out.println("Solicitando limpeza...");
                sala.limpar();
            }

            System.out.println("Agendando horário padrão...");
            // imaginando que sala possui setHorario
            sala.setHorario("14h - 16h");

            return sala;
        };

        Sala s = salaOperacionalSupplier.get();
        System.out.println(s);
    }

    @Test
    void exercicio5SupplierPessoaComHistoricoSocialAndPerfilPsicologico(){
        /*
            5 — Pessoa gerada por Supplier com histórico social e perfil psicológico
            Enunciado:
            Crie um Supplier de pessoa que:
            - atribui profissão baseada em personalidade
            - atribui nível de estresse atual
            - cria um perfil psicossocial do dia

            Ideia chave: Supplier como motor de simulação narrativa.
         */

            Supplier<Pessoa> pessoaProfundaSupplier = () -> {
                Random randomico = new Random();
                List<String> perfis = List.of("Introvertido", "Extrovertido", "Analítico", "Criativo");
                List<String> profissoes = List.of("Designer", "Programador", "Professor", "Médico");

                String perfil = perfis.get(randomico.nextInt(perfis.size()));
                String profissao = profissoes.get(randomico.nextInt(profissoes.size()));

                Pessoa p = new Pessoa("Indivíduo X", randomico.nextInt(60) + 18);
                System.out.println("Perfil psicológico: " + perfil);
                System.out.println("Profissão atribuída: " + profissao);

                return p;
            };

            Pessoa pessoa = pessoaProfundaSupplier.get();
        System.out.println(pessoa);
    }

    @Test
    void exercicio6SupplierHospedeQueCalculaCustoHotelComRegrasComerciaisComplexas(){
        /*
            6 — Hospede com Supplier que calcula custo do hotel com regras comerciais complexas
            Enunciado:

            Crie um Supplier de Hospede que calcule:
            - diária
            - taxa de limpeza
            - desconto para long stay
            - taxa por alta temporada

            Ideia chave: Supplier como motor contábil.
         */

            Supplier<Hospede> hospedeFinanceiro = () -> {
                Random r = new Random();
                int dias = r.nextInt(30) + 1;

                double diaria = 120;
                double limpeza = 30;
                double base = dias * diaria + limpeza;
                double desconto = dias > 10 ? base * 0.15 : 0;
                double altaTemporada = 80;

                double total = base - desconto + altaTemporada;

                Hospede h = new Hospede("Cliente", dias);
                System.out.println("Total a pagar: " + total);

                return h;
            };

            Hospede hosp = hospedeFinanceiro.get();
        System.out.println(hosp);
    }

    @Test
    void exercicio7SupplierAlunoGeraHistoricoEscolarCompleto(){
        /*
            7 — Aluno com Supplier que gera um histórico escolar completo
            Enunciado:

            Crie um Supplier que:
            - gera notas para várias matérias
            - calcula média global
            - gera status (Aprovado, Recuperação, Reprovado)
            - cria um objeto aluno já com relatório pronto

            Ideia chave: Supplier como gerador de relatórios com lógica pesada.
         */

        Supplier<Aluno> alunoAcademico = () -> {
            Random r = new Random();

            double n1 = r.nextDouble() * 10;
            double n2 = r.nextDouble() * 10;
            double n3 = r.nextDouble() * 10;
            double media = (n1 + n2 + n3) / 3;

            String status = media >= 7 ? "Aprovado" :
                            media >= 5 ? "Recuperação" : "Reprovado";

            Aluno a = new Aluno("Lucas", media, 3);
            System.out.println("Média final: " + media);
            System.out.println("Status: " + status);

            return a;
        };

        Aluno aluno = alunoAcademico.get();
        System.out.println(aluno);
    }

    @Test
    void exercicio8SupplierSimulaGeracaoPipelineDeMicrosserviços(){
        /*
            8 — Pedido com Supplier que gera um pipeline de micro-serviços (simulação)
            Enunciado:

            Crie um Supplier que:
            - gera ID sequencial
            - registra o pedido no “serviço de auditoria”
            - calcula impostos
            - calcula prazo de entrega
            - produz o objeto final Pedido

            Ideia chave: Supplier como motor de orquestração de micro-serviços.
         */

        AtomicInteger id = new AtomicInteger(1000);
        Supplier<Pedido> pedidoMicroservicos = () -> {
            int codigo = id.incrementAndGet();
            double valor = 200 + new Random().nextDouble() * 800;

            System.out.println("Auditando pedido " + codigo);
            double impostos = valor * 0.12;

            int prazo = 5 + new Random().nextInt(7); // 5 a 12 dias

            Pedido p = new Pedido(codigo, valor + impostos);
            System.out.println("Pedido " + codigo + " criado. Prazo: " + prazo + " dias.");

            return p;
        };

        Pedido pedido = pedidoMicroservicos.get();
        System.out.println(pedido);
    }
}
