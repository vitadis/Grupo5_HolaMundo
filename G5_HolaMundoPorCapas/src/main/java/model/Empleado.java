package model;

import java.time.LocalDate;
import java.time.Period;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Hodei.Torres
 */
public abstract class Empleado {

    private String usuario;
    private String pass;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;
    private String mail;
    private int telefono;
    private LocalDate fechaNacimiento;
    private Departamento departamento;
    private LocalDate fechaIngreso;

    public Empleado(String usuario, String pass, String dni, String nombre, String apellido1,
            String apellido2, String direccion, String mail, int telefono,
            LocalDate fechaNacimiento, Departamento departamento, LocalDate fechaIngreso) {
        this.usuario = usuario;
        this.pass = pass;
        this.dni = validarDni(dni);
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.direccion = direccion;
        this.mail = validarCorreo(mail);
        this.telefono = validarTelefono(telefono);
        this.fechaNacimiento = validarFechaNac(fechaNacimiento);
        this.departamento = departamento;
        this.fechaIngreso = validarFechaIngreso(fechaIngreso, fechaNacimiento);
    }

    public static String validarDni(String dni) {
        if (dni == null || !dni.trim().matches("\\d{8}[A-Za-z]")) {
            throw new IllegalArgumentException("DNI inválido: deben ser 8 números y 1 letra.");
        }
        return dni.trim().toUpperCase();
    }

    public static String validarCorreo(String mail) {
        if (mail == null || !mail.trim().matches("[\\w.%+-]+@[\\w-]+(\\.[\\w-]+)*\\.[A-Za-z]{2,}")) {
            throw new IllegalArgumentException("Correo inválido: formato esperado usuario@dominio.com");
        }
        return mail.trim();
    }

    public static int validarTelefono(int telefono) {
        if (!String.valueOf(telefono).matches("[6-9]\\d{8}")) {
            throw new IllegalArgumentException("Teléfono inválido: 9 dígitos que empiecen por 6, 7, 8 o 9.");
        }
        return telefono;
    }

    public static LocalDate validarFechaNac(LocalDate fecha) {
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida (nula o futura).");
        }
        return fecha;
    }

    public static LocalDate validarFechaIngreso(LocalDate ingreso, LocalDate nacimiento) {
        if (ingreso == null || ingreso.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de ingreso inválida (nula o futura).");
        }
        if (nacimiento != null && !ingreso.isAfter(nacimiento)) {
            throw new IllegalArgumentException("El ingreso debe ser posterior al nacimiento.");
        }
        return ingreso;
    }

    public int getAntiguedad() {
        return fechaIngreso == null ? 0 : Period.between(fechaIngreso, LocalDate.now()).getYears();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPass() {
        return pass;
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

    public String getMail() {
        return mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setDni(String dni) {
        this.dni = validarDni(dni);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setMail(String mail) {
        this.mail = validarCorreo(mail);
    }

    public void setTelefono(int telefono) {
        this.telefono = validarTelefono(telefono);
    }

    public void setFechaNacimiento(LocalDate fecha) {
        this.fechaNacimiento = validarFechaNac(fecha);
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setFechaIngreso(LocalDate fecha) {

        this.fechaIngreso = validarFechaIngreso(fecha, this.fechaNacimiento);
    }

    @Override
    public String toString() {
        return "Empleado{usuario=" + usuario + ", dni=" + dni + ", nombre=" + nombre
                + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
                + ", direccion=" + direccion + ", mail=" + mail + ", telefono=" + telefono
                + ", fechaNacimiento=" + fechaNacimiento + ", departamento=" + departamento
                + ", fechaIngreso=" + fechaIngreso + '}';
    }
}
