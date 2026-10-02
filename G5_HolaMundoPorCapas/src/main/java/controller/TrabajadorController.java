/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Empleado;

/**
 * FXML Controller class
 *
 * @author Hodei.Torres
 */
public class TrabajadorController implements Initializable {

    //FORMATO PARA PODER USAR LAS FECHAS
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    //DATOS DE LA CABECERA
    @FXML
    private Label lblDniUsuario;
    @FXML
    private Label depart;
    
    //DATOS QUE NO SE MODIFICAN
    @FXML
    private Label nom;
    @FXML
    private Label ape1;
    @FXML
    private Label ape2;
    @FXML
    private Label dni;
    @FXML
    private Label mail;
    @FXML
    private Label tlf;   
    @FXML
    private Label dir;
    @FXML
    private Label sueldo;
    
    //DATOS QUE SE PUEDEN MODIFICAR
    @FXML
    private Label fechaNac;
    @FXML
    private Label fechaIng;
    
    
    //GUARDAD
    private model.Trabajador empleado;
    @FXML
    private Button btnGuardar;
    @FXML
    private GridPane editablesDatos;
    @FXML
    private Button datosEdit;
    @FXML
    private TextField textTlf;
    @FXML
    private TextField textMail;
    @FXML
    private TextField textDir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setTrabajador(model.Trabajador t){
        this.empleado = t;
        
        lblDniUsuario.setText("DNI: " + t.getDni());
        depart.setText("Departamento: " + t.getDepartamento());
        
        nom.setText(t.getNombre());
        ape1.setText(t.getApellido1());
        ape2.setText(t.getApellido2());
        dni.setText(t.getDni());
        mail.setText(t.getMail());
        tlf.setText(String.valueOf(t.getTelefono()));
        dir.setText(t.getDireccion());
        fechaNac.setText(t.getFechaNacimiento().format(FORMATO));
        fechaIng.setText(t.getFechaIngreso().format(FORMATO));
        sueldo.setText(String.valueOf(t.getSueldo())+ "€");
        
        //btnGuardar.setVisible(false);   
        editablesDatos.setVisible(false);
    }
    
    @FXML
    private void editarDatos(){
        editablesDatos.setVisible(true);
        textDir.setText(dir.getText());
        textMail.setText(mail.getText());
        textTlf.setText(tlf.getText());
    }
    
    
    @FXML
    public void guardar() {
        String direccion = textDir.getText().trim();
        if (direccion.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "La dirección no puede estar vacía").showAndWait();
            return;
        }
        
        String correo = textMail.getText().trim();
        if (correo.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "El correo electronico no puede estar vacio.").showAndWait();
            return;
        }
        
        String telefono = textTlf.getText();
        if (telefono.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "El telefono no puede estar vacio").showAndWait();
            return;
        }
        
        try {
                     
            empleado.setDireccion(direccion);
            empleado.setMail(Empleado.validarCorreo(correo));
            empleado.setTelefono(Empleado.validarTelefono(Integer.valueOf(telefono)));
            
            mail.setText(correo);
            dir.setText(direccion);
            tlf.setText(telefono);
            
            editablesDatos.setVisible(false);
            
        } catch(IllegalArgumentException ex){
             new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }
    
        private Runnable onCerrarSesion;

        // Lo llama el login para decidir qué hacer al cerrar sesión.
        public void setOnCerrarSesion(Runnable accion) {
            this.onCerrarSesion = accion;
        }
        
        @FXML
        private void cerrarSesion() {
            if (onCerrarSesion != null) {
                onCerrarSesion.run();
            }
        }
  
}
