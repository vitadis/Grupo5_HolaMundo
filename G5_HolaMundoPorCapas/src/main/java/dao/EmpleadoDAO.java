/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Hodei.Torres
 */

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.Departamento;
import model.Empleado;
import model.Gerente;
import model.Jefe;
import model.Trabajador;

/**
 * Repositorio en memoria de empleados de prueba, compartido por las vistas.
 */
public class EmpleadoDAO {

    private static final Map<String, Empleado> EMPLEADOS = new HashMap<>();

    static {
        Empleado[] datos = {
            // ---- 1 JEFE ----
            new Jefe("jefe", "1234", "10000001S", "Iker", "Etxeberria", "Goikoetxea",
                    "Calle Mayor 1, Bilbao", "iker.etxeberria@empresa.com", 600000001,
                    LocalDate.of(1975, 3, 12), Departamento.ventas, LocalDate.of(2005, 1, 10),2143.21),

            // ---- 2 GERENTES ----
            new Gerente("gerente.ventas", "1234", "20000001Y", "Amaia", "Urrutia", "Zubiaurre",
                    "Calle Ercilla 5, Bilbao", "amaia.urrutia@empresa.com", 611000001,
                    LocalDate.of(1982, 7, 25), Departamento.ventas, LocalDate.of(2012, 9, 1),1994.56),
            new Gerente("gerente.conta", "1234", "20000002F", "Jon", "Garmendia", "Arregi",
                    "Calle Licenciado Poza 12, Bilbao", "jon.garmendia@empresa.com", 611000002,
                    LocalDate.of(1980, 11, 3), Departamento.contabilidad, LocalDate.of(2010, 4, 15),1997.74),

            // ---- 5 TRABAJADORES DE VENTAS ----
            new Trabajador("trab.v1", "1234", "30000001C", "Ane", "Lasa", "Mendizabal",
                    "Calle Colón 3, Bilbao", "ane.lasa@empresa.com", 622000001,
                    LocalDate.of(1990, 1, 14), Departamento.ventas, LocalDate.of(2018, 2, 1),1462.85),
            new Trabajador("trab.v2", "1234", "30000002K", "Unai", "Aranburu", "Olaizola",
                    "Calle Autonomía 8, Bilbao", "unai.aranburu@empresa.com", 622000002,
                    LocalDate.of(1992, 5, 30), Departamento.ventas, LocalDate.of(2019, 6, 17),1462.85),
            new Trabajador("trab.v3", "1234", "30000003E", "Leire", "Ibarra", "Sagarzazu",
                    "Calle Iparraguirre 20, Bilbao", "leire.ibarra@empresa.com", 622000003,
                    LocalDate.of(1988, 9, 8), Departamento.ventas, LocalDate.of(2016, 10, 3),1462.85),
            new Trabajador("trab.v4", "1234", "30000004T", "Mikel", "Goenaga", "Uribe",
                    "Calle Henao 15, Bilbao", "mikel.goenaga@empresa.com", 622000004,
                    LocalDate.of(1995, 12, 21), Departamento.ventas, LocalDate.of(2021, 3, 8),1462.85),
            new Trabajador("trab.v5", "1234", "30000005R", "Nerea", "Zabala", "Aguirre",
                    "Calle Rodríguez Arias 7, Bilbao", "nerea.zabala@empresa.com", 622000005,
                    LocalDate.of(1993, 4, 2), Departamento.ventas, LocalDate.of(2020, 9, 28),1462.85),

            // ---- 5 TRABAJADORES DE CONTABILIDAD ----
            new Trabajador("trab.c1", "1234", "40000001B", "Gorka", "Arana", "Beitia",
                    "Calle Gran Vía 30, Bilbao", "gorka.arana@empresa.com", 633000001,
                    LocalDate.of(1989, 2, 17), Departamento.contabilidad, LocalDate.of(2017, 1, 9),1673.42),
            new Trabajador("trab.c2", "1234", "40000002N", "Maite", "Otegi", "Larrea",
                    "Calle Alameda Rekalde 11, Bilbao", "maite.otegi@empresa.com", 633000002,
                    LocalDate.of(1991, 8, 26), Departamento.contabilidad, LocalDate.of(2018, 11, 12),1673.42),
            new Trabajador("trab.c3", "1234", "40000003J", "Asier", "Agirre", "Elorza",
                    "Calle Estraunza 2, Bilbao", "asier.agirre@empresa.com", 633000003,
                    LocalDate.of(1994, 6, 5), Departamento.contabilidad, LocalDate.of(2020, 2, 24),1673.42),
            new Trabajador("trab.c4", "1234", "40000004Z", "Itziar", "Mugika", "Alonso",
                    "Calle Navarra 9, Bilbao", "itziar.mugika@empresa.com", 633000004,
                    LocalDate.of(1987, 10, 19), Departamento.contabilidad, LocalDate.of(2015, 5, 4),1673.42),
            new Trabajador("trab.c5", "1234", "40000005S", "Xabier", "Landa", "Pérez",
                    "Calle Berastegi 6, Bilbao", "xabier.landa@empresa.com", 633000005,
                    LocalDate.of(1996, 3, 28), Departamento.contabilidad, LocalDate.of(2022, 7, 18),1673.42)
        };

        for (Empleado e : datos) {
            EMPLEADOS.put(e.getDni(), e);
        }
    }

    public static Map<String, Empleado> obtenerTodos() {
        return Collections.unmodifiableMap(EMPLEADOS);
    }

    public static boolean autenticar(String dni, String pass) {
        if (dni == null || pass == null) {
            return false;
        }

        for (Empleado empleado : EMPLEADOS.values()) {
            boolean dniCorrecto = empleado.getDni().equalsIgnoreCase(dni.trim());
            boolean contrasenaCorrecta = empleado.getPass().equals(pass);

            if (dniCorrecto && contrasenaCorrecta) {
                return true;
            }
        }

        return false;
    }

    public static Empleado buscarPorDni(String dni) {
        if (dni == null) {
            return null;
        }

        for (Empleado empleado : EMPLEADOS.values()) {
            if (empleado.getDni().equalsIgnoreCase(dni.trim())) {
                return empleado;
            }
        }

        return null;
    }
}
