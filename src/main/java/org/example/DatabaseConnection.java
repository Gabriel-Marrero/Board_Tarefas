package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:3306/board_tarefas";
        private static final String USER = "root";  // Substitua pelo seu usuário do MySQL
        private static final String PASSWORD = "Marrero@#2000";  // Substitua pela sua senha do MySQL

        public static Connection getConnection() {
            try {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
    }

