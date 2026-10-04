/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
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
import javafx.stage.Stage;
import model.Empleado;

/**
 * Controlador de la vista del trabajador.
 * Gestiona la visualización y edición de los datos personales del empleado,
 * así como la navegación de cierre de sesión.
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
    private Label sueldo;
    @FXML
    private Label fechaNac;
    @FXML
    private Label fechaIng;
    
    //DATOS QUE SE PUEDEN MODIFICAR
    @FXML
    private Label mail;
    @FXML
    private Label tlf;   
    @FXML
    private Label dir;
    
    
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
    @FXML
    private Button btnCerrarSesion;

    /**
     * Inicializa el controlador.
     * En la implementación actual no requiere carga adicional al abrirse la vista.
     *
     * @param url URL del recurso FXML asociado
     * @param rb Recursos de internacionalización asociados
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Carga la información del trabajador en la vista y prepara los campos
     * no editables con los datos del empleado.
     *
     * @param t trabajador cuyos datos se deben mostrar en la interfaz
     */
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
        
        editablesDatos.setManaged(false);
        editablesDatos.setVisible(false);
    }
    
    /**
     * Activa los campos editables para modificar los datos del trabajador.
     * Se rellenan con los valores actuales para que el usuario pueda editarlos.
     */
    @FXML
    private void editarDatos(){
        editablesDatos.setManaged(true);
        editablesDatos.setVisible(true);
        textDir.setText(dir.getText());
        textMail.setText(mail.getText());
        textTlf.setText(tlf.getText());
    }
    
    /**
     * Guarda los datos modificados del trabajador validando que la dirección,
     * el correo y el teléfono no estén vacíos y cumplan los requisitos de la entidad.
     * Si la validación falla, se muestra un mensaje de error al usuario.
     */
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
            editablesDatos.setManaged(false);
            
        } catch(IllegalArgumentException ex){
             new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }
        
    /**
     * Cierra la sesión del trabajador y regresa a la pantalla de inicio.
     * La ventana actual se cierra y se abre la vista de inicio de sesión.
     */
    @FXML
    private void cerrarSesion() {
        try {
            Stage ventanaActual = (Stage) btnCerrarSesion.getScene().getWindow();
            ventanaActual.close();
            InstanciarEscena.abrirNuevaVentana("/view/PantallaInicioView.fxml", "Iniciar sesión");
        } catch (IOException ex) {
            System.out.println("Error al volver al login: " + ex.getMessage());
        }
    }
  
}
