package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.EmpleadoDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.*;

/**
 * Controlador de la vista del Jefe.
 *
 * Muestra todos los empleados en una tabla y permite filtrarlos por DNI y por
 * tipo de trabajador (Todos, Gerente o Trabajador).
 *
 * Así funciona la aplicación, paso a paso: 1. JavaFX abre la vista y ejecuta
 * initialize(). 2. initialize() llama a cargarDatosTabla(), que crea las
 * columnas y llena la tabla con los empleados. 3. Los empleados se piden al DAO
 * (listaEmpleados()) y se convierten al modelo que se muestra en pantalla
 * (cargarObservableList()). 4. Si el jefe escribe en el campo de DNI, se
 * ejecuta filtradoDni(). Si pulsa un botón (Todos, Gerente, Trabajador), se
 * ejecuta filtrarPorTipo(). 5. Los dos métodos anteriores llaman a
 * aplicarFiltros(), que oculta o muestra las filas de la tabla según lo que se
 * haya escrito y el botón elegido.
 *
 * @author Joel
 * @see EmpleadoDAO
 * @see VisualizacionEmpleados
 */
public class JefeController {

    // ============================= FILTERS ===================================
    /**
     * Lista que usa la tabla. Permite ocultar y mostrar empleados sin perder
     * los datos originales.
     */
    private FilteredList<VisualizacionEmpleados> listaFiltrada;

    /**
     * Tipo de trabajador elegido con los botones: "Todos", "Gerente" o
     * "Trabajador". Al inicio es "Todos".
     */
    private String tipoFiltro = "Todos";

    // ======================== COMPONENTS FXML ================================
    /**
     * Tabla donde se muestran los empleados.
     */
    @FXML
    private TableView<VisualizacionEmpleados> tablaComponent;

    /**
     * Campo donde escribes, y te filtra segun el contenido de los getters dni,
     * correo, departamento
     */
    @FXML
    private TextField tfFiltros;

    // =========================================================================
    // ========================== METHODS FXML =================================
    // =========================================================================
    /**
     * Se ejecuta automáticamente cuando se abre la vista. Aquí cargamos los
     * datos de la tabla.
     *
     * @see #cargarDatosTabla()
     */
    @FXML
    public void initialize() {
        cargarDatosTabla();
    }

    /**
     * Abrirá una ventana con el perfil del empleado para poder modificarlo.
     * Todavía está pendiente de hacer.
     */
    @FXML
    public void verPerfil() {
        System.out.println("Abrir dialog, del perfil, para poder modificarlo");
        // dni, nombre, apellido1, apellido2, dirección, mail, teléfono, fecha nacimiento, departamento, fecha ingreso, tipo de trabajador
    }

    /**
     * Filtra la tabla por DNI mientras se escribe. Se conecta en el FXML al
     * evento de teclado del campo de texto. Solo llama a aplicarFiltros(), que
     * lee lo que hay escrito.
     *
     * @see #aplicarFiltros()
     */
    @FXML
    public void filtrado() {
        aplicarFiltros();
    }

    /**
     * Filtra la tabla por tipo de trabajador. Lo usan los tres botones (Todos,
     * Gerente y Trabajador). Lee el texto del botón que se pulsó, lo guarda en
     * tipoFiltro y llama a aplicarFiltros().
     *
     * @param event el clic en el botón, de aquí sacamos qué botón fue
     * @see #aplicarFiltros()
     */
    @FXML
    public void filtrarPorTipo(ActionEvent event) {
        Button boton = (Button) event.getSource();
        tipoFiltro = boton.getText();
        aplicarFiltros();
    }

    // =========================================================================
    // ========================== FILTER LOGIC =================================
    // =========================================================================
    /**
     * Aplica los filtros a la tabla (el de texto y el de tipo a la vez).
     *
     * Si la lista todavía no existe, no hace nada. Si existe, lee el texto
     * escrito en el campo de búsqueda (sin espacios y en minúsculas) y cambia
     * el predicado de la lista. El predicado es una condición que se revisa
     * para cada empleado: si da true, el empleado se muestra; si da false, se
     * oculta.
     *
     * Un empleado se muestra solo si cumple las dos condiciones: -
     * coincideTexto: el campo está vacío, o el texto escrito aparece en el DNI,
     * en el email o en el departamento del empleado (basta con que coincida uno
     * de los tres). - coincideTipo: el botón elegido es "Todos", o el tipo del
     * empleado es igual al del botón elegido.
     *
     * La búsqueda no distingue entre mayúsculas y minúsculas, y si algún dato
     * del empleado es null simplemente no coincide.
     *
     * @see #filtradoDni()
     * @see #filtrarPorTipo(ActionEvent)
     */
    private void aplicarFiltros() {
        if (listaFiltrada == null) {
            return;
        }

        String textoFiltro = tfFiltros.getText() == null ? "" : tfFiltros.getText().trim();

        listaFiltrada.setPredicate(emp -> {
            boolean coincideDni = emp.getDni() != null
                    && emp.getDni().toLowerCase().contains(textoFiltro);

            boolean coincideEmail = emp.getEmail() != null
                    && emp.getEmail().toLowerCase().contains(textoFiltro);

            boolean coincideDpto = emp.getDepartamento() != null
                    && emp.getDepartamento().toLowerCase().contains(textoFiltro);

            boolean coincideTipo = tipoFiltro.equalsIgnoreCase("Todos")
                    || (emp.getTipoTrabajador() != null
                    && emp.getTipoTrabajador().equalsIgnoreCase(tipoFiltro));

            return (textoFiltro.isEmpty() || coincideDni || coincideEmail || coincideDpto) && coincideTipo;
        });
    }

    // =========================================================================
    // ========================== TABLE LOADING ================================
    // =========================================================================
    /**
     * Crea la tabla de empleados.
     *
     * Pasos: 1. Pide la lista de empleados a cargarObservableList(). 2. Crea
     * las columnas y las agrega a la tabla. 3. Le dice a cada columna qué dato
     * del empleado debe mostrar. 4. Pone la lista en la tabla (envuelta en la
     * lista filtrada, que al inicio muestra a todos).
     */
    public void cargarDatosTabla() {
        ObservableList<VisualizacionEmpleados> listaEmpleados = cargarObservableList();

        TableColumn<VisualizacionEmpleados, String> colDni = new TableColumn<>("DNI");
        TableColumn<VisualizacionEmpleados, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<VisualizacionEmpleados, String> colApellido1 = new TableColumn<>("Apellido 1");
        TableColumn<VisualizacionEmpleados, String> colApellido2 = new TableColumn<>("Apellido 2");
        TableColumn<VisualizacionEmpleados, String> colDireccion = new TableColumn<>("Dirección");
        TableColumn<VisualizacionEmpleados, String> colEmail = new TableColumn<>("Email");
        TableColumn<VisualizacionEmpleados, String> colTelefono = new TableColumn<>("Teléfono");
        TableColumn<VisualizacionEmpleados, String> colFechaNacimiento = new TableColumn<>("Fecha Nacimiento");
        TableColumn<VisualizacionEmpleados, String> colDepartamento = new TableColumn<>("Departamento");
        TableColumn<VisualizacionEmpleados, String> colFechaIngreso = new TableColumn<>("Fecha Ingreso");
        TableColumn<VisualizacionEmpleados, String> colTipoTrabajador = new TableColumn<>("Tipo de trabajador");

        tablaComponent.getColumns().add(colDni);
        tablaComponent.getColumns().add(colNombre);
        tablaComponent.getColumns().add(colApellido1);
        tablaComponent.getColumns().add(colApellido2);
        tablaComponent.getColumns().add(colDireccion);
        tablaComponent.getColumns().add(colEmail);
        tablaComponent.getColumns().add(colTelefono);
        tablaComponent.getColumns().add(colFechaNacimiento);
        tablaComponent.getColumns().add(colDepartamento);
        tablaComponent.getColumns().add(colFechaIngreso);
        tablaComponent.getColumns().add(colTipoTrabajador);

        colDni.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDni()));
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colApellido1.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getApellido1()));
        colApellido2.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getApellido2()));
        colDireccion.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDireccion()));
        colEmail.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEmail()));
        colTelefono.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTelefono()));
        colFechaNacimiento.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFechaNacimiento()));
        colDepartamento.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDepartamento()));
        colFechaIngreso.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getFechaIngreso()));
        colTipoTrabajador.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTipoTrabajador()));

        // La tabla muestra la lista filtrada (al inicio muestra todo)
        listaFiltrada = new FilteredList<>(listaEmpleados, e -> true);
        tablaComponent.setItems(listaFiltrada);
    }

    // =========================================================================
    // ======================= AUXILIARY METHODS ===============================
    // =========================================================================
    /**
     * Prepara la lista de empleados para la tabla.
     *
     * Toma cada Empleado, lo convierte en un VisualizacionEmpleados (el modelo
     * que se muestra en pantalla) y guarda todos en una ObservableList, que es
     * el tipo de lista que entiende la tabla.
     *
     * @see #listaEmpleados()
     */
    private ObservableList<VisualizacionEmpleados> cargarObservableList() {
        List<Empleado> listEmp = listaEmpleados();

        List<VisualizacionEmpleados> listVisEmp = listEmp.stream()
                .map(empleado -> new VisualizacionEmpleados(empleado))
                .collect(Collectors.toList());
        listVisEmp.removeIf(e -> e.getTipoTrabajador().equals("Jefe"));

        ObservableList<VisualizacionEmpleados> listaEmpleados = FXCollections.observableArrayList();
        listaEmpleados.addAll(listVisEmp);
        return listaEmpleados;
    }

    /**
     * Pide todos los empleados al DAO (la clase que habla con la base de
     * datos).
     *
     * @see EmpleadoDAO
     */
    private List<Empleado> listaEmpleados() {
        return new ArrayList<>(EmpleadoDAO.obtenerTodos().values());
    }
}
