/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import dao.EmpleadoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.*;

/**
 *
 * @author Joel
 */
public class JefeController {
    
    

    @FXML
    public void initialize() {
        cargarDatosTabla();
    }

    @FXML
    private TableView<VisualizacionEmpleados> tablaComponent;
    @FXML
    private TextField tfDni;
    
    
    
    @FXML
    public void verPerfil() {
        System.out.println("Abrir dialog, del perfil, para poder modificarlo");
        // dni, nombre, apellido1, apellido2, dirección, mail, teléfono, fecha nacimiento, departamento, fecha ingreso, tipo de trabajador
        
    }

    @FXML
    public void buscarPorDni() {
        System.out.println("Aplicar filtro de la tabla");
    }
    
    @FXML
    public void filtradoDni(){
        String texto = tfDni.getText();
        System.out.println(texto);
    }
    public void filtradoPorDni(){
    
    
    }
    
    

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
        
        colDni.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getDni()));
        colNombre.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getNombre()));
        colApellido1.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getApellido1()));
        colApellido2.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getApellido2()));
        colDireccion.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getDireccion()));
        colEmail.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getEmail()));
        colTelefono.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getTelefono()));
        colFechaNacimiento.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getFechaNacimiento()));
        colDepartamento.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getDepartamento()));
        colFechaIngreso.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getFechaIngreso()));
        colTipoTrabajador.setCellValueFactory(cell
                -> new SimpleStringProperty(cell.getValue().getTipoTrabajador()));

        tablaComponent.setItems(listaEmpleados);
    }
    
    private ObservableList<VisualizacionEmpleados> cargarObservableList() {
        List<Empleado> listEmp = listaEmpleados();

        List<VisualizacionEmpleados> listVisEmp = listEmp.stream()
                .map(empleado -> new VisualizacionEmpleados(empleado))
                .collect(Collectors.toList());

        ObservableList<VisualizacionEmpleados> listaEmpleados = FXCollections.observableArrayList();
        listaEmpleados.addAll(listVisEmp);
        return listaEmpleados;

    }

    private List<Empleado> listaEmpleados() {
        return new ArrayList<>(EmpleadoDAO.obtenerTodos().values());
    }
}
