import excecao.EstacionamentoCheioException;
import excecao.EstacionamentoVazioException;
import excecao.PlacaDuplicadaException;

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
                        String placa = scanner.nextLine().toUpperCase();

                        if (placa.length() == 0) {
                            System.out.println("Placa inválida. A placa não pode ser vazia.");
                        } else {
                            try {
                                estacionamento.estacionarCarro(new Carro(placa));
                            } catch (PlacaDuplicadaException | EstacionamentoCheioException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Digite a placa do carro a remover: ");
                        String placaRemover = scanner.nextLine().toUpperCase();

                        if (placaRemover.length() == 0) {
                            System.out.println("Placa inválida. A placa não pode ser vazia.");
                        } else {

                            if (estacionamento.estacionamentoVazio()) {
                                System.out.println("O estacionamento está vazio. Não há carros para remover.");

                            } else {

                                try {

                                    estacionamento.removerCarro(placaRemover);

                                } catch (EstacionamentoVazioException e) {
                                    System.out.println(e.getMessage());


                                }

                            }
                        }
                        break;

                    case 3:
                        estacionamento.relatorioDiario();
                        break;

                    case 4:
                        System.out.println("Saindo...");
                        return;

                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 4.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}