package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.VisualizacionEmpleados;

/** Controlador del diálogo que muestra el perfil resumido de un empleado. */
public class DialogPerfilController {

    /** Crea el controlador del diálogo de perfil. */
    public DialogPerfilController() {
    }

    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDni;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido1;
    @FXML
    private Label lblApellido2;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblFechaNacimiento;
    @FXML
    private Label lblDepartamento;
    @FXML
    private Label lblFechaIngreso;
    @FXML
    private Label lblTipoTrabajador;

    /**
     * Rellena los labels con los datos del trabajador.
     *
     * @param trabajador objeto con los datos a visualizar.
     */
    public void setTrabajador(VisualizacionEmpleados trabajador) {
        lblTitulo.setText("Perfil del " + trabajador.getTipoTrabajador());
        lblDni.setText(trabajador.getDni());
        lblNombre.setText(trabajador.getNombre());
        lblApellido1.setText(trabajador.getApellido1());
        lblApellido2.setText(trabajador.getApellido2());
        lblDireccion.setText(trabajador.getDireccion());
        lblEmail.setText(trabajador.getEmail());
        lblTelefono.setText(trabajador.getTelefono());
        lblFechaNacimiento.setText(trabajador.getFechaNacimiento());
        lblDepartamento.setText(trabajador.getDepartamento());
        lblFechaIngreso.setText(trabajador.getFechaIngreso());
        lblTipoTrabajador.setText(trabajador.getTipoTrabajador());
    }

    /** Cierra la ventana del diálogo de perfil asociado al botón pulsado.
     *
     * @param event evento generado por el botón de cierre
     */
    @FXML
    private void cerrar(ActionEvent event) {
        Button boton = (Button) event.getSource();
        Stage stage = (Stage) boton.getScene().getWindow();
        stage.close();
    }
}
