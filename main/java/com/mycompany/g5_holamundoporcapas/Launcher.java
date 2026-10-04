package com.mycompany.g5_holamundoporcapas;

/**
 * Clase principal encargada de iniciar la aplicación.
 * Se utiliza como intermediaria para evitar restricciones de arranque con JavaFX.
 * 
 * @author anazk
 */
public class Launcher {

    /**
     * Constructor por defecto.
     */
    public Launcher() {
    }

    /**
     * Método principal que lanza la ejecución de la aplicación.
     * 
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        App.main(args);
    }
}