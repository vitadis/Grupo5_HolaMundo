/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/** Controlador contenedor que carga la vista principal del jefe. */
public class MainController {

    /** Crea el controlador contenedor de la vista principal. */
    public MainController() {
    }

    @FXML
    private StackPane contenedor;

    /**
     * Carga la vista del jefe dentro del contenedor definido en el FXML.
     *
     * @throws IOException si no se puede cargar el recurso FXML
     */
    @FXML
    public void initialize() throws IOException {
        Region  vista = FXMLLoader.load(getClass().getResource("/view/jefeView.fxml"));
        vista.setMaxWidth(Double.MAX_VALUE);
        vista.setMaxHeight(Double.MAX_VALUE);
        contenedor.getChildren().setAll(vista);
    }
}
