package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/** Utilidades para iniciar la aplicación JavaFX y gestionar sus ventanas. */
public final class InstanciarEscena extends Application {

    /** Crea el gestor de escenas requerido por JavaFX. */
    public InstanciarEscena() {
    }

    /**
    * Carga una vista FXML en la ventana indicada y devuelve su controlador.
    * Cuando la ventana ya tiene una escena, reemplaza su raíz para conservar
    * el estado de la ventana.
    *
    * @param <T> tipo del controlador de la vista cargada
    * @param stage ventana en la que se mostrará la vista
    * @param ruta ruta del recurso FXML desde la raíz de recursos
    * @param titulo título que se asignará a la ventana
    * @return controlador asociado al recurso FXML
    * @throws IOException si el recurso FXML no existe o no se puede cargar
     */
    public static <T> T cambiarVista(Stage stage, String ruta, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(InstanciarEscena.class.getResource(ruta));
        Parent root = loader.load();
        if (stage.getScene() == null) {
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.setMaximized(true);
            stage.show();
        } else {
            stage.getScene().setRoot(root);
            stage.setTitle(titulo);
        }
        return loader.getController();
    }

    /**
     * Crea una ventana nueva y carga en ella la vista indicada.
     *
     * @param ruta ruta del recurso FXML desde la raíz de recursos
     * @param titulo título de la nueva ventana
     * @return ventana creada y mostrada
     * @throws IOException si el recurso FXML no existe o no se puede cargar
     */
    public static Stage abrirNuevaVentana(String ruta, String titulo) throws IOException {
        Stage stage = new Stage();
        cambiarVista(stage, ruta, titulo);
        return stage;
    }
    /**
     * Muestra un diálogo de error al usuario.
     *
     * @param mensaje texto explicativo del error
     */
    public static void mostrarError(String mensaje) {
        mostrarMensaje(Alert.AlertType.ERROR, "Error", mensaje);
    }
    /**
     * Muestra un diálogo informativo al usuario.
     *
     * @param mensaje información que se mostrará al usuario
     */
    public static void mostrarInfo(String mensaje) {
        mostrarMensaje(Alert.AlertType.INFORMATION, "Información", mensaje);
    }
    /**
     * Instancia un tipo de mensaje segun lo que se le pase por los parametros.
     */
    private static void mostrarMensaje(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    /**
        * Inicializa la ventana principal con la pantalla de inicio de sesión.
        *
        * @param stage ventana principal de JavaFX
        * @throws Exception si no se puede cargar o mostrar la vista inicial
     */
        @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PantallaInicioView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Iniciar sesión");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
