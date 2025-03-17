package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TarefaUI {
    public static void main(String[] args) {
        // Criação do JFrame
        JFrame frame = new JFrame("Gerenciador de Tarefas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Criação do JPanel para organizar os componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2)); // Ajustado para 6 linhas

        // Componentes da interface
        JLabel idLabel = new JLabel("ID da Tarefa:");
        JTextField idField = new JTextField();

        JLabel tituloLabel = new JLabel("Título:");
        JTextField tituloField = new JTextField();

        JLabel descricaoLabel = new JLabel("Descrição:");
        JTextArea descricaoArea = new JTextArea();

        JLabel statusLabel = new JLabel("Status:");
        String[] statusOptions = {"pendente", "progresso", "concluída"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);

        // Botões de ação
        JButton adicionarButton = new JButton("Adicionar Tarefa");
        JButton atualizarButton = new JButton("Atualizar Tarefa");
        JButton removerButton = new JButton("Remover Tarefa");

        // Adicionando os componentes ao painel
        panel.add(idLabel);
        panel.add(idField);
        panel.add(tituloLabel);
        panel.add(tituloField);
        panel.add(descricaoLabel);
        panel.add(descricaoArea);
        panel.add(statusLabel);
        panel.add(statusComboBox);
        panel.add(adicionarButton);
        panel.add(atualizarButton);
        panel.add(removerButton);

        // Adicionando o painel ao frame
        frame.add(panel);
        frame.setVisible(true);

        // Instância do DAO
        TarefaDAO dao = new TarefaDAO();

        // Ação ao clicar no botão "Adicionar Tarefa"
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = tituloField.getText().trim();
                String descricao = descricaoArea.getText().trim();
                String status = (String) statusComboBox.getSelectedItem();

                if (descricao.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "A descrição da tarefa não pode estar vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                dao.adicionarTarefa(titulo, descricao, status);

                tituloField.setText("");
                descricaoArea.setText("");
                statusComboBox.setSelectedIndex(0);

                JOptionPane.showMessageDialog(frame, "Tarefa adicionada com sucesso!");
            }
        });

        // Ação ao clicar no botão "Atualizar Tarefa"
        atualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());
                    String titulo = tituloField.getText().trim();
                    String descricao = descricaoArea.getText().trim();
                    String status = (String) statusComboBox.getSelectedItem();

                    if (descricao.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "A descrição da tarefa não pode estar vazia!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    boolean sucesso = dao.atualizarTarefa(id, titulo, descricao, status);
                    if (sucesso) {
                        JOptionPane.showMessageDialog(frame, "Tarefa atualizada com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Erro ao atualizar a tarefa! Verifique o ID.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "ID inválido! Digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação ao clicar no botão "Remover Tarefa"
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());

                    int confirm = JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja remover esta tarefa?", "Confirmação", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean sucesso = dao.removerTarefa(id);
                        if (sucesso) {
                            JOptionPane.showMessageDialog(frame, "Tarefa removida com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Erro ao remover a tarefa! Verifique o ID.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "ID inválido! Digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}