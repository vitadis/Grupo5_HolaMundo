package controller;

import dao.EmpleadoDAO;
import java.io.IOException;
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
import javafx.stage.Stage;
import model.Empleado;
import model.Gerente;
import model.Trabajador;

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

    private Gerente gerenteLogueado;
    private Runnable onCerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setOnCerrarSesion(Runnable accion) {
        this.onCerrarSesion = accion;
    }

    public void setGerente(Gerente gerente) {
        this.gerenteLogueado = gerente;

        lblNombreGerente.setText(gerente.getNombre() + " " + gerente.getApellido1());
        lblNombreDepartamento.setText("Departamento: " + gerente.getDepartamento().toString());

        cargarListaEmpleados();
    }

    private void cargarListaEmpleados() {
        VBoxEmpleados.getChildren().clear();
        Map<String, Empleado> mapEmpleados = EmpleadoDAO.obtenerTodos();

        for (Empleado emp : mapEmpleados.values()) {
            if (emp.getDepartamento().equals(gerenteLogueado.getDepartamento())) {
                HBox filaEmpleado = crearFilaEmpleado(emp);
                VBoxEmpleados.getChildren().add(filaEmpleado);
            }
        }
    }

    private HBox crearFilaEmpleado(Empleado emp) {
        HBox fila = new HBox(15);
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.getStyleClass().add("employee-card");

        Label lblDni = new Label(emp.getDni());
        lblDni.getStyleClass().add("text-muted");

        Label lblNombre = new Label(emp.getNombre() + " " + emp.getApellido1() + " " + emp.getApellido2());
        lblNombre.getStyleClass().add("text-heading");
        Label lblDpto = new Label(emp.getDepartamento().toString());
        lblDpto.getStyleClass().add("text-brand-secondary");

        VBox datosBox = new VBox(3, lblNombre, new HBox(10, lblDni, lblDpto));
        HBox.setHgrow(datosBox, Priority.ALWAYS);

        Button btnVer = new Button("Ver Perfil");
        btnVer.getStyleClass().addAll("button", "button-secondary");
        btnVer.setOnAction(e -> {
            try {
                Stage stage = (Stage) btnVer.getScene().getWindow();
                TrabajadorController controlador = InstanciarEscena.cambiarVista(
                        stage,
                        "/view/TrabajadorView.fxml",
                        "Perfil de " + emp.getNombre()
                );
                if (emp instanceof Trabajador) {
                    controlador.setTrabajador((Trabajador) emp);
                }
            } catch (IOException ex) {
                System.out.println("Error al cargar la vista: " + ex.getMessage());
            }
        });

        fila.getChildren().addAll(datosBox, btnVer);
        return fila;
    }

    @FXML
    public void cerrarSesion() {
        if (onCerrarSesion != null) {
            onCerrarSesion.run();
        }
    }
}
