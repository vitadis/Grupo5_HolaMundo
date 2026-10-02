module com.mycompany.g5_holamundoporcapas {
    requires javafx.controls;
    requires javafx.fxml;

    opens model to javafx.base;
    opens com.mycompany.g5_holamundoporcapas to javafx.fxml;

    opens controller to javafx.fxml, javafx.graphics;
    exports controller;
    exports com.mycompany.g5_holamundoporcapas;
}