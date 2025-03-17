package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarefaDAO {
    // Define os dados da conexão
    private static final String URL = "jdbc:mysql://localhost:3306/board_tarefas";
    private static final String USER = "root";
    private static final String PASSWORD = "Marrero@#2000"; // Troque pela senha correta

    // Método para conectar ao banco de dados
    public Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro de conexão com o banco de dados.");
            e.printStackTrace();
            return null;
        }
    }

    // Método para adicionar tarefa ao banco de dados
    public boolean adicionarTarefa(String titulo, String descricao, String status) {
        if (titulo == null || titulo.isEmpty() || descricao == null || descricao.isEmpty()) {
            System.out.println("Erro: Título e descrição são obrigatórios!");
            return false;
        }

        String sql = "INSERT INTO tarefas (titulo, descricao, status) VALUES (?, ?, ?)";
        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, descricao);
            stmt.setString(3, status);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar tarefa.");
            return false;
        }
    }

    // Método para remover uma tarefa do banco de dados
    public boolean removerTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao remover tarefa.");
            return false;
        }
    }

    // Método atualizado para retornar boolean
    public boolean atualizarTarefa(int id, String titulo, String descricao, String status) {
        if (status == null || (!status.equals("pendente") && !status.equals("em progresso") && !status.equals("concluída"))) {
            System.out.println("Erro: Status inválido");
            return false;
        }

        String query = "UPDATE tarefas SET titulo = ?, descricao = ?, status = ? WHERE id = ?";
        try (Connection connection = conectar();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, titulo);
            statement.setString(2, descricao);
            statement.setString(3, status);
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tarefa.");
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todas as tarefas do banco de dados
    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas";

        try (Connection conn = conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descricao"),
                        rs.getString("status")
                );
                tarefas.add(tarefa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar tarefas.");
        }

        return tarefas;
    }
}