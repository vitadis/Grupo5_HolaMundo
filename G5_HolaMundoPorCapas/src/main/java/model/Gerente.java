/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Hodei.Torres
 */
public class Gerente extends Empleado{
    
    public Gerente(String usuario, String pass, String dni, String nombre, String apellido1, String apellido2, String direccion, String mail, int telefono, LocalDate fechaNacimiento, Departamento departamento, LocalDate fechaIngreso) {
        super(usuario, pass, dni, nombre, apellido1, apellido2, direccion, mail, telefono, fechaNacimiento, departamento, fechaIngreso);
    }
    
    
    
}
