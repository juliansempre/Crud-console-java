package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        Statement stmt = null;

        try {
            // Conecta ao banco de dados
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            // Cria tabela
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS users " +
                    "(id INT(11) NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " email VARCHAR(255), " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sqlCreateTable);

            while (true) {
                System.out.println("Selecione uma opção:");
                System.out.println("1 - Inserir usuário");
                System.out.println("2 - Atualizar usuário");
                System.out.println("3 - Excluir usuário");
                System.out.println("4 - Listar usuários");
                System.out.println("5 - Sair");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Digite o nome do usuário:");
                        String name = scanner.next();
                        System.out.println("Digite o email do usuário:");
                        String email = scanner.next();

                        // Insere usuário
                        String sqlInsert = "INSERT INTO users (name, email) VALUES ('" + name + "', '" + email + "')";
                        stmt.executeUpdate(sqlInsert);
                        System.out.println("Usuário inserido com sucesso.");
                        break;

                    case 2:
                        System.out.println("Digite o ID do usuário que deseja atualizar:");
                        int idUpdate = scanner.nextInt();
                        System.out.println("Digite o novo nome do usuário:");
                        String nameUpdate = scanner.next();
                        System.out.println("Digite o novo email do usuário:");
                        String emailUpdate = scanner.next();

                        // Atualiza usuário
                        String sqlUpdate = "UPDATE users SET name = '" + nameUpdate + "', email = '" + emailUpdate + "' WHERE id = " + idUpdate;
                        int rowsUpdated = stmt.executeUpdate(sqlUpdate);

                        if (rowsUpdated > 0) {
                            System.out.println("Usuário atualizado com sucesso.");
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;

                    case 3:
                        System.out.println("Digite o ID do usuário que deseja excluir:");
                        int idDelete = scanner.nextInt();

                        // Deleta usuário
                        String sqlDelete = "DELETE FROM users WHERE id = " + idDelete;
                        int rowsDeleted = stmt.executeUpdate(sqlDelete);

                        if (rowsDeleted > 0) {
                            System.out.println("Usuário excluído com sucesso.");
                        } else {
                            System.out.println("Usuário não encontrado.");
                        }
                        break;

                    case 4:
                        // Lista usuários
                        String sqlSelect = "SELECT * FROM users";
                        ResultSet rs = stmt.executeQuery(sqlSelect);

                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nameList = rs.getString("name");
                            String emailList = rs.getString("email");
                            System.out.println("ID: " + id + ", Nome: " + nameList + ", Email: " + emailList);
                        }
                        break;

                    case 5:
                        // Sai do programa
                        System.out.println("Programa encerrado.");
                        scanner.close();
                        stmt.close();
                        conn.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}