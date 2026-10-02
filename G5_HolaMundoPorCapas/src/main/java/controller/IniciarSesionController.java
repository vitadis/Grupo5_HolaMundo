package controller;

import dao.EmpleadoDAO;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Empleado;
import model.Gerente;
import model.Jefe;
import model.Trabajador;

public class IniciarSesionController {

    @FXML
    private TextField IntroUsur;
    @FXML
    private PasswordField introContrasena;
    @FXML
    private Label lblError;

    @FXML
    private void iniciarSesion() {
        String dni = IntroUsur.getText().trim();
        String contrasena = introContrasena.getText();

        if (dni.isEmpty()) {
            mostrarError("El DNI está vacío.");
            IntroUsur.requestFocus();
            return;
        }
        if (contrasena.isEmpty()) {
            mostrarError("La contraseña está vacía.");
            introContrasena.requestFocus();
            return;
        }

        boolean credencialesCorrectas = EmpleadoDAO.autenticar(dni, contrasena);
        if (!credencialesCorrectas) {
            mostrarError("DNI o contraseña incorrectos.");
            introContrasena.requestFocus();
            return;
        }

        Empleado empleado = EmpleadoDAO.buscarPorDni(dni);
        abrirVistaSegunCargo(empleado);
    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
    }

    private void abrirVistaSegunCargo(Empleado empleado) {
        Stage stage = (Stage) IntroUsur.getScene().getWindow();

        try {
            switch (empleado.getClass().getSimpleName()) {
                case "Trabajador":
                    TrabajadorController controladorTrabajador
                        = InstanciarEscena.cambiarVista(stage, "/view/TrabajadorView.fxml", "Perfil del trabajador");
                    controladorTrabajador.setTrabajador((Trabajador) empleado);
                    controladorTrabajador.setOnCerrarSesion(() -> abrirLoginEnVentanaNueva(stage));
                    break;
                case "Gerente":
                    InstanciarEscena.cambiarVista(stage, "/view/GerenteView.fxml", "Vista del gerente");
                    break;
                case "Jefe":
                    InstanciarEscena.cambiarVista(stage, "/view/jefeView.fxml", "Vista del jefe");
                    break;
                default:
                    InstanciarEscena.mostrarError("No hay una vista asignada para el cargo: "+ empleado.getClass().getSimpleName());
                    break;
            }
        } catch (IOException ex) {
            InstanciarEscena.mostrarError("No se pudo cargar la vista del empleado: " + ex.getMessage());
        }
    }

    private void abrirLoginEnVentanaNueva(Stage ventanaPerfil) {
        try {
            InstanciarEscena.abrirNuevaVentana(
                    "/view/PantallaInicioView.fxml", "Iniciar sesión");
            ventanaPerfil.close();
        } catch (IOException ex) {
            InstanciarEscena.mostrarError("No se pudo abrir el inicio de sesión: " + ex.getMessage());
        }
    }
}
