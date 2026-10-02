/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.Departamento;
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

        if (listEmp == null || listEmp.isEmpty()) {
            return null;
        }

        List<VisualizacionEmpleados> listVisEmp = listEmp.stream()
                .map(empleado -> new VisualizacionEmpleados(empleado))
                .collect(Collectors.toList());

        ObservableList<VisualizacionEmpleados> listaEmpleados = FXCollections.observableArrayList();
        listaEmpleados.addAll(listVisEmp);
        return listaEmpleados;

    }

    private List<Empleado> listaEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Trabajador(
                "juan", "1234", "12345678A", "Juan", "García", "López",
                "Calle Mayor 1", "juan@gmail.com", 612345678,
                LocalDate.of(1995, 3, 15), Departamento.contabilidad,
                LocalDate.of(2020, 5, 10)
        ));
        empleados.add(new Trabajador(
                "ana", "1234", "23456789B", "Ana", "Martínez", "Pérez",
                "Calle Bilbao 12", "ana@gmail.com", 623456789,
                LocalDate.of(1998, 7, 22), Departamento.contabilidad,
                LocalDate.of(2022, 2, 1)
        ));

        empleados.add(new Trabajador(
                "carlos", "1234", "34567890C", "Carlos", "López", "Gómez",
                "Avenida Euskadi 5", "carlos@gmail.com", 634567890,
                LocalDate.of(1992, 11, 8), Departamento.contabilidad,
                LocalDate.of(2018, 9, 15)
        ));

        empleados.add(new Jefe(
                "maria", "1234", "45678901D", "María", "Fernández", "Ruiz",
                "Calle Navarra 20", "maria@gmail.com", 645678901,
                LocalDate.of(1988, 1, 30), Departamento.ventas,
                LocalDate.of(2016, 4, 20)
        ));

        empleados.add(new Jefe(
                "david", "1234", "56789012E", "David", "Sánchez", "Moreno",
                "Calle Autonomía 8", "david@gmail.com", 656789012,
                LocalDate.of(1985, 6, 12), Departamento.ventas,
                LocalDate.of(2015, 1, 10)
        ));

        empleados.add(new Gerente(
                "laura", "1234", "67890123F", "Laura", "González", "Díaz",
                "Calle Gran Vía 30", "laura@gmail.com", 667890123,
                LocalDate.of(1980, 9, 25), Departamento.ventas,
                LocalDate.of(2010, 3, 1)
        ));

        empleados.add(new Trabajador(
                "miguel", "1234", "78901234G", "Miguel", "Rodríguez", "Navarro",
                "Calle Iparraguirre 14", "miguel@gmail.com", 678901234,
                LocalDate.of(2000, 2, 18), Departamento.ventas,
                LocalDate.of(2024, 6, 10)
        ));

        empleados.add(new Trabajador(
                "lucia", "1234", "89012345H", "Lucía", "Jiménez", "Ortega",
                "Calle Ercilla 7", "lucia@gmail.com", 689012345,
                LocalDate.of(1997, 12, 5), Departamento.ventas,
                LocalDate.of(2021, 10, 4)
        ));

        empleados.add(new Jefe(
                "pablo", "1234", "90123456I", "Pablo", "Morales", "Castro",
                "Calle Hurtado 18", "pablo@gmail.com", 698123456,
                LocalDate.of(1987, 4, 17), Departamento.ventas,
                LocalDate.of(2017, 7, 3)
        ));

        empleados.add(new Gerente(
                "sofia", "1234", "01234567J", "Sofía", "Vázquez", "Iglesias",
                "Calle Alameda 25", "sofia@gmail.com", 612987654,
                LocalDate.of(1978, 10, 11), Departamento.ventas,
                LocalDate.of(2008, 11, 17)
        ));
        return empleados;
    }
}
