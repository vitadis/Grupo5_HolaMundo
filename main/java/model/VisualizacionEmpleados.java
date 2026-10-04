/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Proyección textual de los datos de un empleado para mostrarlos en tablas y
 * diálogos de la interfaz.
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

    /**
     * Crea una proyección con los datos visibles del empleado indicado.
     *
     * @param trabajador empleado cuyos datos se van a presentar
     * @throws NullPointerException si el empleado o alguno de los datos que se
     * convierten a texto es nulo
     */
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

    /**
     * Obtiene el documento nacional de identidad.
     *
     * @return DNI del empleado
     */
    public String getDni() {
        return dni;
    }

    /**
     * Obtiene el nombre de pila.
     *
     * @return nombre del empleado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el primer apellido.
     *
     * @return primer apellido del empleado
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Obtiene el segundo apellido.
     *
     * @return segundo apellido del empleado
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Obtiene la dirección postal.
     *
     * @return domicilio del empleado
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Obtiene la dirección de correo electrónico.
     *
     * @return correo electrónico del empleado
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene el teléfono como texto.
     *
     * @return número de teléfono del empleado
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Obtiene la fecha de nacimiento como texto.
     *
     * @return fecha de nacimiento del empleado
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Obtiene el departamento como texto.
     *
     * @return departamento del empleado
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Obtiene la fecha de incorporación como texto.
     *
     * @return fecha de ingreso del empleado
     */
    public String getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Obtiene la categoría del empleado.
     *
     * @return tipo de empleado: jefe, gerente o trabajador
     */
    public String getTipoTrabajador() {
        return tipoTrabajador;
    }
}
