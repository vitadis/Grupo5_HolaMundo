package com.mycompany.g5_holamundoporcapas;

import controller.InstanciarEscena;
import javafx.application.Application;

/** Punto de entrada de la aplicación JavaFX. */
public class App {

    /** Crea el punto de entrada de la aplicación. */
    public App() {
    }

    /**
     * Inicia la aplicación delegando la creación de la interfaz en
     * {@link InstanciarEscena}.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {
        Application.launch(InstanciarEscena.class, args);
    }

}