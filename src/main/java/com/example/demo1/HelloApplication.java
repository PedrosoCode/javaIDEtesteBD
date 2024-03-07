package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Criar rótulos
        Label nameLabel = new Label("Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label emailLabel = new Label("Email:");

        // Criar campos de texto
        TextField nameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();

        // Criar botão
        Button submitButton = new Button("Submit");

        // Definir ação do botão
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();

            // Inserir dados no banco de dados
            insertData(name, lastName, email);
        });

        // Criar um layout de grade para organizar os elementos
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Adicionar elementos ao layout de grade
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(emailLabel, 0, 2);
        gridPane.add(emailField, 1, 2);
        gridPane.add(submitButton, 1, 3);

        // Criar cena e definir no palco
        Scene scene = new Scene(gridPane, 320, 240);
        stage.setTitle("Form");
        stage.setScene(scene);
        stage.show();
    }

    // Método para inserir dados no banco de dados
    private void insertData(String name, String lastName, String email) {
        String url = "jdbc:mysql://localhost:3306/java";
        String user = "root";
        String password = "@Inspiron1";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO tb_teste (nome, sobrenome, email) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados no banco de dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
