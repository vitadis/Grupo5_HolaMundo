package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import dao.EmpleadoDAO;
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

/**
 * Controlador para la vista del panel de Gerente en JavaFX. Se encarga de
 * gestionar la interfaz de usuario específica para los gerentes, mostrando su
 * información personal y listando los empleados que pertenecen a su mismo
 * departamento. Permite visualizar el perfil de los trabajadores y gestionar el
 * cierre de sesión.
 * @author An
 */
public class GerenteViewController implements Initializable {

    /** Crea el controlador de la vista del gerente. */
    public GerenteViewController() {
    }

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

    /**
     * Método llamado automáticamente por JavaFX tras cargar el archivo FXML.
     *
     * @param url La ubicación utilizada para resolver las rutas relativas del
     * objeto raíz, o null si no se conoce.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null
     * si no se localizó.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Configura los datos del gerente que ha iniciado sesión, actualiza las
     * etiquetas de la cabecera con su nombre y departamento, y desencadena la
     * carga de la lista de empleados.
     *
     * @param gerente El objeto Gerente que ha iniciado sesión.
     */
    public void setGerente(Gerente gerente) {
        this.gerenteLogueado = gerente;

        lblNombreGerente.setText(gerente.getNombre() + " " + gerente.getApellido1());
        lblNombreDepartamento.setText("Departamento: " + gerente.getDepartamento().toString());

        cargarListaEmpleados();
    }

    /**
     * Limpia la vista actual y carga la lista de empleados obtenidos desde el
     * map que esta en el paquete DAO. Filtra los empleados para mostrar
     * únicamente aquellos que pertenecen al mismo departamento que el gerente
     * logueado.
     */
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

    /**
     * Crea un contenedor visual (HBox) con la información resumida de un
     * empleado y un botón para acceder a su perfil detallado.
     *
     * @param emp El objeto Empleado del cual se mostrarán los datos.
     * @return Un objeto HBox configurado con las etiquetas de texto y el botón
     * de acción correspondiente.
     */
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

    /**
     * Manejador de eventos vinculado al botón de cerrar sesión en el FXML. 
     * Si se pulsa el boton vuelves a la ventana de inicio de sesion
     */
    @FXML
    public void cerrarSesion() {
        try {
            Stage ventanaActual = (Stage) btnCerrarSesion.getScene().getWindow();
            ventanaActual.close();
            InstanciarEscena.abrirNuevaVentana("/view/PantallaInicioView.fxml", "Iniciar sesión");
        } catch (IOException ex) {
            System.out.println("Error al volver al login: " + ex.getMessage());
        }
    }
}
