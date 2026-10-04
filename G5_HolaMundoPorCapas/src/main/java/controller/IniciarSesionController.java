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

public class IniciarSesionController {

    @FXML
    private TextField IntroUsur;
    @FXML
    private PasswordField introContrasena;
    @FXML
    private Label lblError;

    @FXML
    /**
     * Función al darle al boton de IniciarSesion.
     *  - Comprueba los campos de DNI y contraseña si estan rellenados, encaso 
     *    de que no lo esten, muestra el error en el label.
     *  - Despues, llama a la funcion autenticar(), comprobando que el DNI y la 
     *    contraseña coincidan.
     *  - Por ultimo, busca el usuario loggeado y se lo pasa a abrirVistaSegunCargo().
     */
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
     * Muestra el error en un Label.
     */
    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
    }
    
    /**
     * Gestiona el empleado pasado para abrir la siguente ventana acorde a su 
     * cargo.
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
