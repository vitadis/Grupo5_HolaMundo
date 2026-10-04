package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public final class InstanciarEscena extends Application{

    /**
     * Función que genera un ventana que se adapta a la resoluación de la pantalla del usuario.
     *  -La letra T es un parametro generico, es decir no esta definido el tipo todavi, dentro de la misma funcion se define.
     *  -La condicion gestiona el como se redimensiona al entrar en una nueva escena.
     *      Si cambias solo el ROOT, el Stage no se redimensiona y se mantiene maximizado.
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

    public static Stage abrirNuevaVentana(String ruta, String titulo) throws IOException {
        Stage stage = new Stage();
        cambiarVista(stage, ruta, titulo);
        return stage;
    }
    /**
     * Define la alerta a tipo error, y se lo pasa a mostrarMensaje().
     */
    public static void mostrarError(String mensaje) {
        mostrarMensaje(Alert.AlertType.ERROR, "Error", mensaje);
    }
    /**
     * Define la alerta tipo infromativo, y se lo pasa a mostrarMensaje().
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

    @Override
    /**
     * Instancia la primera pantalla al iniciar la App.
     */
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/PantallaInicioView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Iniciar sesión");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
