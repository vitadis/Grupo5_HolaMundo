/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Joel
 */
public class VisualizacionEmpleados {

    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;
    private String email;
    private String telefono;
    private String fechaNacimiento;
    private String departamento;
    private String fechaIngreso;
    private String tipoTrabajador;

    public VisualizacionEmpleados(Empleado trabajador) {

        this.dni = trabajador.getDni();
        this.nombre = trabajador.getNombre();
        this.apellido1 = trabajador.getApellido1();
        this.apellido2 = trabajador.getApellido2();
        this.direccion = trabajador.getDireccion();
        this.email = trabajador.getMail();
        this.telefono = trabajador.getTelefono() + "";
        this.fechaNacimiento = trabajador.getFechaNacimiento().toString();
        this.departamento = trabajador.getDepartamento().toString();
        this.fechaIngreso = trabajador.getFechaIngreso().toString();

        if (trabajador instanceof Jefe) {
            this.tipoTrabajador = "Jefe";
        } else if (trabajador instanceof Gerente) {
            this.tipoTrabajador = "Gerente";
        } else if (trabajador instanceof Trabajador) {
            this.tipoTrabajador = "Trabajador";
        }
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getTipoTrabajador() {
        return tipoTrabajador;
    }
}
