/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/** Empleado que desempeña funciones de gerencia en un departamento. */
public class Gerente extends Empleado {

    /**
     * Crea un gerente con los datos personales y laborales indicados.
     *
     * @param usuario nombre de usuario de acceso
     * @param pass contraseña de acceso
     * @param dni documento nacional de identidad
     * @param nombre nombre del gerente
     * @param apellido1 primer apellido
     * @param apellido2 segundo apellido
     * @param direccion domicilio
     * @param mail correo electrónico
     * @param telefono teléfono de contacto
     * @param fechaNacimiento fecha de nacimiento
     * @param departamento departamento que dirige
     * @param fechaIngreso fecha de incorporación
     * @param sueldo sueldo del gerente
     * @throws IllegalArgumentException si algún dato validado por {@link Empleado}
     * no cumple su formato
     */
    public Gerente(String usuario, String pass, String dni, String nombre, String apellido1, String apellido2, String direccion, String mail, int telefono, LocalDate fechaNacimiento, Departamento departamento, LocalDate fechaIngreso, double sueldo) {
        super(usuario, pass, dni, nombre, apellido1, apellido2, direccion, mail, telefono, fechaNacimiento, departamento, fechaIngreso, sueldo);
    }

    
  
    
   
    
    
    
    
    
}
