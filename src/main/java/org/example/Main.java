package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TarefaDAO dao = new TarefaDAO();

        while (true) {
            System.out.println("\n=== GERENCIADOR DE TAREFAS ===");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Atualizar Tarefa");
            System.out.println("4. Remover Tarefa");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Status (pendente/em progresso/concluída): ");
                    String status = scanner.nextLine();
                    dao.adicionarTarefa(titulo, descricao, status);
                    System.out.println("Tarefa adicionada com sucesso!");
                    break;

                case 2:
                    List<Tarefa> tarefas = dao.listarTarefas();
                    System.out.println("\n=== LISTA DE TAREFAS ===");
                    for (Tarefa t : tarefas) {
                        System.out.println(t);
                    }
                    break;

                case 3:
                    System.out.print("ID da tarefa para atualizar: ");
                    int idAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha
                    System.out.print("Novo título: ");
                    String novoTitulo = scanner.nextLine();
                    System.out.print("Nova descrição: ");
                    String novaDescricao = scanner.nextLine();
                    System.out.print("Novo status: ");
                    String novoStatus = scanner.nextLine();
                    dao.atualizarTarefa(idAtualizar, novoTitulo, novaDescricao, novoStatus);
                    System.out.println("Tarefa atualizada!");
                    break;

                case 4:
                    System.out.print("ID da tarefa para remover: ");
                    int idRemover = scanner.nextInt();
                    dao.removerTarefa(idRemover);
                    System.out.println("Tarefa removida!");
                    break;

                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}