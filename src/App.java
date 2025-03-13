import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento(4);
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                estacionamento.mensagemBoasVnindas();
                estacionamento.opcoesMenu();

                int opcao = -1;
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    scanner.next(); // Limpar o buffer do scanner
                    continue; // Volta para o início do loop
                }

                switch (opcao) {
                    case 1:
                        System.out.print("Digite a placa do carro: ");
                        String placa = scanner.nextLine().toUpperCase(); // Garantir que a placa seja em maiúsculas

                        if (placa.isEmpty()) {
                            System.out.println("Placa inválida. A placa não pode ser vazia.");
                        } else {
                            if (estacionamento.placaJaExiste(placa)) {
                                System.out.println("Erro: Já existe um carro com a placa " + placa + " no estacionamento ou na fila de espera.");
                            } else {
                                estacionamento.estacionarCarro(new Carro(placa));
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Digite a placa do carro a remover: ");
                        String placaRemover = scanner.nextLine().toUpperCase(); // Garantir que a placa seja em maiúsculas

                        if (placaRemover.isEmpty()) {
                            System.out.println("Placa inválida. A placa não pode ser vazia.");
                        } else {
                            estacionamento.removerCarro(placaRemover);
                        }
                        break;

                    case 3:
                        estacionamento.relatorioDiario();
                        break;

                    case 4:
                        System.out.println("Saindo...");
                        return; // Finaliza o programa

                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 4.");
                }
            }
        } finally {
            // Fechar o scanner para evitar vazamento de recursos
            scanner.close();
        }
    }
}