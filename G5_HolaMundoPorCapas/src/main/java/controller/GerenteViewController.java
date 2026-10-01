/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import dao.EmpleadoDao;
import dao.EmpleadoDaoImpl;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Empleado;

/**
 * FXML Controller class
 *
 * @author anazk
 */
public class GerenteViewController implements Initializable {

    @FXML
    private GridPane headerBar;
    @FXML
    private Label lblNombreGerente;
    @FXML
    private Label lblNombreDepartamento;
    @FXML
    private Button btnCerrarSesion;
    @FXML
    private VBox VBoxEmpleados;

    private final EmpleadoDao empleadoDAO = EmpleadoDaoImpl.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListaEmpleados();
    }

    private void cargarListaEmpleados() {
        VBoxEmpleados.getChildren().clear();

        Map<String, Empleado> mapEmpleados = empleadoDAO.obtenerTodos();

        for (Empleado emp : mapEmpleados.values()) {
            HBox filaEmpleado = crearFilaEmpleado(emp);
            VBoxEmpleados.getChildren().add(filaEmpleado);
        }

    }

    private HBox crearFilaEmpleado(Empleado emp) {
        HBox fila = new HBox(15);
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.getStyleClass().add("employee-card");

        // Datos del empleado
        Label lblDni = new Label(emp.getDni());
        lblDni.setStyle("-fx-font-weight: bold; -fx-text-fill: #64748b;");

        Label lblNombre = new Label(emp.getNombre() + " " + emp.getApellido1() + " " + emp.getApellido2());
        lblNombre.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label lblDpto = new Label(emp.getDepartamento().toString());
        lblDpto.setStyle("-fx-text-fill: #2563eb;");

        // Espaciador para empujar el botón al extremo derecho
        VBox datosBox = new VBox(3, lblNombre, new HBox(10, lblDni, lblDpto));
        HBox.setHgrow(datosBox, Priority.ALWAYS);

        // Botón para ver o editar el perfil
        Button btnVer = new Button("Ver Perfil");
        btnVer.getStyleClass().add("btn-secondary");
        btnVer.setOnAction(e -> {
            System.out.println("Seleccionado: " + emp.getNombre() + " (" + emp.getDni() + ")");
        });

        fila.getChildren().addAll(datosBox, btnVer);
        return fila;
    }
}
