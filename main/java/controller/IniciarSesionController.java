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
import model.Trabajador;

/** Controlador de la pantalla de inicio de sesión. */
public class IniciarSesionController {

    /** Crea el controlador de inicio de sesión. */
    public IniciarSesionController() {
    }

    @FXML
    private TextField IntroUsur;
    @FXML
    private PasswordField introContrasena;
    @FXML
    private Label lblError;

    /**
        * Valida las credenciales introducidas y abre la vista correspondiente al
        * cargo del empleado autenticado.
     */
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
    /**
        * Muestra un mensaje de error en la etiqueta de la pantalla.
        *
        * @param mensaje texto que se mostrará
     */
    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
    }
    
    /**
        * Abre la vista asociada al cargo del empleado autenticado.
        *
        * @param empleado empleado cuya vista se debe mostrar
     */
    private void abrirVistaSegunCargo(Empleado empleado) {
        Stage stage = (Stage) IntroUsur.getScene().getWindow();

        try {
            switch (empleado.getClass().getSimpleName()) {
                case "Trabajador":
                    TrabajadorController controladorTrabajador
                            = InstanciarEscena.cambiarVista(stage, "/view/TrabajadorView.fxml", "Perfil del trabajador");
                    controladorTrabajador.setTrabajador((Trabajador) empleado);
                    break;
                case "Gerente":
                    GerenteViewController controladorGerente
                            = InstanciarEscena.cambiarVista(stage, "/view/GerenteView.fxml", "Vista del gerente");
                    controladorGerente.setGerente((Gerente) empleado);
                    break;
                case "Jefe":
                    InstanciarEscena.cambiarVista(stage, "/view/jefeView.fxml", "Vista del jefe");
                    break;
                default:
                    InstanciarEscena.mostrarError("No hay una vista asignada para el cargo: " + empleado.getClass().getSimpleName());
                    break;
            }
        } catch (IOException ex) {
            InstanciarEscena.mostrarError("No se pudo cargar la vista del empleado: " + ex.getMessage());
        }
    }

    /**
     * Abre una nueva ventana de inicio de sesión y cierra la ventana de perfil.
     *
     * @param ventanaPerfil ventana de perfil que se va a cerrar
     */
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
