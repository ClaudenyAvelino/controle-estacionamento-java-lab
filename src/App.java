import excecao.EstacionamentoCheioException;
import excecao.EstacionamentoVazioException;
import excecao.PlacaDuplicadaException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Criação de um objeto Estacionamento com capacidade para 4 carros
        Estacionamento estacionamento = new Estacionamento(4);
        Scanner scanner = new Scanner(System.in);

        try {
            // Loop principal do aplicativo
            while (true) {
                // Exibe a mensagem de boas-vindas e as opções do menu
                estacionamento.mensagemBoasVnindas();
                estacionamento.opcoesMenu();

                int opcao = -1; // Inicializa a variável de opção

                try {
                    // Lê a opção inserida pelo usuário
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Consome a nova linha do input
                } catch (InputMismatchException e) {
                    // Caso o usuário insira um valor inválido (não numérico)
                    System.out.println("Entrada inválida! Por favor, insira um número.");
                    scanner.next(); // Limpa o buffer do scanner
                    continue; // Volta ao início do loop para tentar novamente
                }

                // Lógica de controle das opções do menu
                switch (opcao) {
                    case 1:
                        // Opção 1: Estacionar um carro
                        System.out.print("Digite a placa do carro: ");
                        String placa = scanner.nextLine().toUpperCase();

                        // Verifica se a placa inserida é válida
                        if (placa.length() == 0) {
                            System.out.println("Placa inválida. A placa não pode ser vazia.");
                        } else {
                            try {
                                // Tenta estacionar o carro
                                estacionamento.estacionarCarro(new Carro(placa));
                            } catch (PlacaDuplicadaException | EstacionamentoCheioException e) {
                                // Captura e trata exceções caso a placa já exista ou o estacionamento esteja cheio
                                System.out.println(e.getMessage());
                            }
                        }
                        break;

                    case 2:
                        // Opção 2: Remover um carro
                        System.out.print("Digite a placa do carro a remover: ");
                        String placaRemover = scanner.nextLine().toUpperCase();

                        // Verifica se a placa inserida é válida
                        if (placaRemover.length() == 0) {
                            System.out.println("Placa inválida. A placa não pode ser vazia.");
                        } else {
                            // Verifica se o estacionamento está vazio antes de tentar remover um carro
                            if (estacionamento.estacionamentoVazio()) {
                                System.out.println("O estacionamento está vazio. Não há carros para remover.");
                            } else {
                                try {
                                    // Tenta remover o carro do estacionamento
                                    estacionamento.removerCarro(placaRemover);
                                } catch (EstacionamentoVazioException e) {
                                    // Captura e trata exceção caso o estacionamento esteja vazio
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        break;

                    case 3:
                        // Opção 3: Gerar o relatório diário
                        estacionamento.relatorioDiario();
                        break;

                    case 4:
                        // Opção 4: Sair do programa
                        System.out.println("Saindo...");
                        return; // Finaliza o programa

                    default:
                        // Caso a opção inserida seja inválida
                        System.out.println("Opção inválida. Por favor, escolha uma opção de 1 a 4.");
                }
            }
        } finally {
            // Fecha o scanner ao final do programa para evitar vazamento de recursos
            scanner.close();
        }
    }
}
