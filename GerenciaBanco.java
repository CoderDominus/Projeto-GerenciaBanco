import java.util.InputMismatchException;
import java.text.DecimalFormat;
import java.util.Scanner;

// Classe que representa os dados pessoais do cliente
class DadosPessoais {
    private String nome;
    private String sobrenome;
    private String cpf;

    // Construtor para inicializar os dados pessoais
    public DadosPessoais(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    // Getters para acessar os dados pessoais
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }
}

// Classe que representa as operações bancárias
class OperacoesBancarias {
    private double saldo;

    // Construtor para inicializar o saldo como zero
    public OperacoesBancarias() {
        this.saldo = 0.00;
    }

    // Método para consultar o saldo
    public double consultarSaldo() {
        return saldo;
    }

    // Método para depositar dinheiro na conta
    public void depositar(double valor) {
        saldo += valor;
    }

    // Método para sacar dinheiro da conta
    public boolean sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            return false;
        }
    }
}

// Classe principal que gerencia os dados e operações bancárias do cliente
public class GerenciaBanco {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        @SuppressWarnings("unused") // Pensando em uma escalabilidade futura resolvo manter a var `dados´
        DadosPessoais dados = cadastrarDadosPessoais(scanner); // Cadastra os dados pessoais do cliente
        OperacoesBancarias operacoes = new OperacoesBancarias(); // Inicializa as operações bancárias

        int escolha;
        do {
            exibirMenu(); // Exibe o menu de opções
            escolha = lerOpcao(scanner); // Lê a opção escolhida pelo usuário
            switch (escolha) {
                case 1:
                    exibirSaldo(operacoes); // Exibe o saldo da conta
                    break;
                case 2:
                    realizarDeposito(scanner, operacoes); // Realiza um depósito na conta
                    break;
                case 3:
                    realizarSaque(scanner, operacoes); // Realiza um saque da conta
                    break;
                case 4:
                    System.out.println("\n\u001B[32mObrigado por utilizar nosso sistema. Até logo!\u001B[0m"); // Mensagem de saída
                    break;
                default:
                    System.out.println("\n\u001B[31mOpção inválida. Por favor, escolha uma opção válida.\u001B[0m"); // Mensagem de opção inválida
            }
        } while (escolha != 4);

        // Mensagem de despedida
        System.out.println("\u001B[32mDesconectando...\u001B[0m");
        try {
            Thread.sleep(5000); // Espera por 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(
                "Emoji chorando:\r\uD83D\uDE2D \uD83D\uDE2D \uD83D\uDE2D \uD83D\uDE2D \uD83D\uDE2D \uD83D\uDE2D");
    }

    // Método para ler a opção escolhida pelo usuário
    private static int lerOpcao(Scanner scanner) {
        while (true) {
            try {
                System.out.print("\nEscolha uma opção: ");
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mOpção inválida. Por favor, escolha uma opção válida.\u001B[0m");
                scanner.next(); // Limpa o buffer do scanner
            }
        }
    }

    // Método para cadastrar os dados pessoais do cliente
    private static DadosPessoais cadastrarDadosPessoais(Scanner scanner) {
        System.out.println("\n\u001B[31mSeja Bem-Vindo ao sistema! Antes de prosseguir.\u001B[0m");
        System.out.println("\nPor favor, insira seus dados pessoais;");
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.println("\n\u001B[32mDados cadastrados com sucesso!\u001B[0m");
        return new DadosPessoais(nome, sobrenome, cpf);
    }

    // Método para exibir o menu de opções
    private static void exibirMenu() {
        System.out.println("\n\u001B[32m======= MENU =======\u001B[0m");
        System.out.println("Olá, este é nosso menu! Agora você tem acesso à:\n");
        System.out.println("1. Ver Saldo");
        System.out.println("2. Realizar Depósito");
        System.out.println("3. Realizar Saque");
        System.out.println("4. Sair");
        System.out.println("\n\u001B[32m======= MENU =======\u001B[0m");
    }

    // Método para exibir o saldo da conta
    private static void exibirSaldo(OperacoesBancarias operacoes) {
        DecimalFormat df = new DecimalFormat("R$ #,##0.00"); // Formato para duas casas decimais
        System.out.println("Seu saldo é:" + df.format(operacoes.consultarSaldo()));
        pausa();
    }

    // Método para realizar um depósito na conta
    private static void realizarDeposito(Scanner scanner, OperacoesBancarias operacoes) {
        System.out.print("Digite o valor a ser depositado: R$");
        double valor = scanner.nextDouble();
        operacoes.depositar(valor);
        System.out.println("\u001B[32mDepósito realizado com sucesso!\u001B[0m");
        pausa();
    }

    // Método para realizar um saque da conta
    private static void realizarSaque(Scanner scanner, OperacoesBancarias operacoes) {
        System.out.print("Digite o valor a ser sacado: R$");
        double valor = scanner.nextDouble();
        boolean saqueRealizado = operacoes.sacar(valor);
        if (saqueRealizado) {
            System.out.println("\u001B[32mSaque realizado com sucesso!\u001B[0m");
        } else {
            System.out.println("\u001B[31mSaldo insuficiente para realizar o saque.\u001B[0m");
        }
        pausa();
    }

    // Método para pausar e aguardar a entrada do usuário
    @SuppressWarnings("resource")
    private static void pausa() {
        System.out.println("\nPressione Enter para continuar...");
        new Scanner(System.in).nextLine();
    }
}
