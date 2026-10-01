module com.mycompany.g5_holamundoporcapas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.g5_holamundoporcapas to javafx.fxml;
    exports com.mycompany.g5_holamundoporcapas;
}
