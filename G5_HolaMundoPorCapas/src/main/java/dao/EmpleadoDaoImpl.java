/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import model.Departamento;
import model.Empleado;
import model.Gerente;
import model.Jefe;
import model.Trabajador;

/**
 *
 * @author anazk
 */
public class EmpleadoDaoImpl implements EmpleadoDao {

    private static EmpleadoDaoImpl instance;

    private final Map<String, Empleado> mapaEmpleados;

    private EmpleadoDaoImpl() {
        mapaEmpleados = new HashMap<>();

        mapaEmpleados.put("12345678A", new Gerente(
                "ana.gomez",
                "1234",
                "12345678A",
                "Ana",
                "Gómez",
                "Pérez",
                "Calle Gran Vía 12, Bilbao",
                "ana.gomez@empresa.com",
                611223344,
                LocalDate.of(1985, 4, 15),
                Departamento.contabilidad,
                LocalDate.of(2015, 6, 1)
        ));

        mapaEmpleados.put("87654321B", new Jefe(
                "carlos.m",
                "1234",
                "87654321B",
                "Carlos",
                "Méndez",
                "Ruiz",
                "Av. de la Libertad 45, Bilbao",
                "carlos.mendez@empresa.com",
                622334455,
                LocalDate.of(1990, 8, 20),
                Departamento.ventas,
                LocalDate.of(2018, 2, 15)
        ));

        mapaEmpleados.put("11223344C", new Trabajador(
                "lucia.f",
                "1234",
                "11223344C",
                "Lucía",
                "Fernández",
                "Sáez",
                "Plaza Circular 3, Bilbao",
                "lucia.fernandez@empresa.com",
                711998877,
                LocalDate.of(1995, 11, 5),
                Departamento.contabilidad,
                LocalDate.of(2021, 9, 1)
        ));

        mapaEmpleados.put("99887766D", new Trabajador(
                "marcos.s",
                "1234",
                "99887766D",
                "Marcos",
                "Silva",
                "Etxeberria",
                "Calle Autonomía 88, Bilbao",
                "marcos.silva@empresa.com",
                699112233,
                LocalDate.of(1998, 2, 28),
                Departamento.contabilidad,
                LocalDate.of(2022, 1, 10)
        ));
    }

    public static EmpleadoDaoImpl getInstance() {
        if (instance == null) {
            instance = new EmpleadoDaoImpl();
        }
        return instance;
    }

    @Override
    public Map<String, Empleado> obtenerTodos() {
      Map<String, Empleado> empleados = new HashMap<>();
      for (Empleado e : mapaEmpleados.values()){
          empleados.put(e.getDni(), e);
      }
      return empleados;
    }
}
