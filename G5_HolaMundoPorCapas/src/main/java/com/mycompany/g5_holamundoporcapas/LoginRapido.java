/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Hodei.Torres
 */
package com.mycompany.g5_holamundoporcapas;

import dao.EmpleadoDAO;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Empleado;

/**
 * Login provisional SOLO PARA PRUEBAS. Ejecuta esta clase directamente (Run File).
 * Los campos vienen rellenos con un trabajador de prueba.
 */
public class LoginRapido extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Login (pruebas)");
        mostrarLogin(stage);
        stage.show();
    }

    private void mostrarLogin(Stage stage) {
        Label titulo = new Label("Login de pruebas");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField txtUsuario = new TextField("trab.v1");
        PasswordField txtPass = new PasswordField();
        txtPass.setText("1234");

        Label lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");

        Button btnEntrar = new Button("Entrar");
        btnEntrar.setDefaultButton(true);
        btnEntrar.setOnAction(ev -> {
            Empleado encontrado = null;
            for (Empleado e : EmpleadoDAO.EMPLEADOS.values()) {
                if (e.getUsuario().equals(txtUsuario.getText().trim())
                        && e.getPass().equals(txtPass.getText())) {
                    encontrado = e;
                    break;
                }
            }

            if (encontrado == null) {
                lblError.setText("Usuario o contraseña incorrectos");
            } else if (encontrado instanceof model.Trabajador) {
                abrirTrabajador(stage, (model.Trabajador) encontrado);
            } else {
                new Alert(Alert.AlertType.INFORMATION,
                        "Ventana de " + encontrado.getClass().getSimpleName()
                        + " aún no implementada").showAndWait();
            }
        });

        VBox root = new VBox(10, titulo, txtUsuario, txtPass, btnEntrar, lblError);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        stage.setScene(new Scene(root, 320, 280));
    }

    private void abrirTrabajador(Stage stage, model.Trabajador t) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trabajador.fxml"));
            Parent root = loader.load();

            trabajador ctrl = loader.getController();
            ctrl.setTrabajador(t);
            ctrl.setOnCerrarSesion(() -> mostrarLogin(stage));

            stage.setScene(new Scene(root));
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, "No se pudo cargar trabajador.fxml: " + ex.getMessage()).showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
