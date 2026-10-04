package model;

import java.time.LocalDate;
import java.time.Period;

/**
 * Representa los datos comunes a los empleados de la organización y aplica
 * validaciones a los datos de identificación y contacto.
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
    private double sueldo;

    /**
     * Crea un empleado con sus datos personales, laborales y de acceso.
     *
     * @param usuario nombre de usuario de acceso
     * @param pass contraseña de acceso
     * @param dni documento nacional de identidad
     * @param nombre nombre del empleado
     * @param apellido1 primer apellido
     * @param apellido2 segundo apellido
     * @param direccion domicilio del empleado
     * @param mail correo electrónico
     * @param telefono teléfono de contacto
     * @param fechaNacimiento fecha de nacimiento
     * @param departamento departamento al que pertenece
     * @param fechaIngreso fecha de incorporación a la organización
     * @param sueldo sueldo del empleado
     * @throws IllegalArgumentException si el DNI, correo, teléfono o fecha de
     * nacimiento no cumplen las reglas de validación
     */
    public Empleado(String usuario, String pass, String dni, String nombre, String apellido1,
            String apellido2, String direccion, String mail, int telefono,
            LocalDate fechaNacimiento, Departamento departamento, LocalDate fechaIngreso, double sueldo) {
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
        this.fechaIngreso = fechaIngreso;
        this.sueldo = sueldo;
    }

    /**
     * Valida un DNI y lo normaliza eliminando espacios exteriores y poniendo
     * su letra en mayúscula.
     *
     * @param dni valor que se desea validar
     * @return DNI normalizado
     * @throws IllegalArgumentException si el valor no contiene ocho dígitos y
     * una letra, o es {@code null}
     */
    public static String validarDni(String dni) {
        if (dni == null || !dni.trim().matches("\\d{8}[A-Za-z]")) {
            throw new IllegalArgumentException("DNI inválido: deben ser 8 números y 1 letra.");
        }
        return dni.trim().toUpperCase();
    }

    /**
     * Valida y recorta un correo electrónico.
     *
     * @param mail dirección que se desea validar
     * @return dirección sin espacios exteriores
     * @throws IllegalArgumentException si el formato no es válido o el valor
     * es {@code null}
     */
    public static String validarCorreo(String mail) {
        if (mail == null || !mail.trim().matches("[\\w.%+-]+@[\\w-]+(\\.[\\w-]+)*\\.[A-Za-z]{2,}")) {
            throw new IllegalArgumentException("Correo inválido: formato esperado usuario@dominio.com");
        }
        return mail.trim();
    }

    /**
     * Comprueba que el teléfono tenga nueve dígitos y comience por un número
     * entre 6 y 9.
     *
     * @param telefono número de teléfono que se desea validar
     * @return el teléfono validado
     * @throws IllegalArgumentException si el número no cumple el formato
     */
    public static int validarTelefono(int telefono) {
        if (!String.valueOf(telefono).matches("[6-9]\\d{8}")) {
            throw new IllegalArgumentException("Teléfono inválido: 9 dígitos que empiecen por 6, 7, 8 o 9.");
        }
        return telefono;
    }

    /**
     * Comprueba que la fecha de nacimiento no sea nula ni futura.
     *
     * @param fecha fecha que se desea validar
     * @return la fecha validada
     * @throws IllegalArgumentException si la fecha es nula o posterior al día
     * actual
     */
    public static LocalDate validarFechaNac(LocalDate fecha) {
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida (nula o futura).");
        }
        return fecha;
    }

    /**
     * Obtiene el sueldo actual.
     *
     * @return sueldo actual del empleado
     */
    public double getSueldo() {
        return sueldo;
    }

    /**
     * Actualiza el sueldo del empleado.
     *
     * @param sueldo nuevo sueldo del empleado
     */
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Calcula los años completos transcurridos desde la fecha de ingreso.
     *
     * @return antigüedad en años, o cero si no hay fecha de ingreso
     */
    public int getAntiguedad() {
        return fechaIngreso == null ? 0 : Period.between(fechaIngreso, LocalDate.now()).getYears();
    }

    /**
     * Obtiene el nombre de usuario utilizado para acceder a la aplicación.
     *
     * @return nombre de usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Obtiene la contraseña utilizada para acceder a la aplicación.
     *
     * @return contraseña
     */
    public String getPass() {
        return pass;
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
    public String getMail() {
        return mail;
    }

    /**
     * Obtiene el número de teléfono de contacto.
     *
     * @return teléfono del empleado
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * Obtiene la fecha de nacimiento.
     *
     * @return fecha de nacimiento del empleado
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Obtiene el departamento asignado.
     *
     * @return departamento al que pertenece el empleado
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Obtiene la fecha de incorporación a la organización.
     *
     * @return fecha de ingreso del empleado
     */
    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * Actualiza el nombre de usuario de acceso.
     *
     * @param usuario nuevo nombre de usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Actualiza la contraseña de acceso.
     *
     * @param pass nueva contraseña
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Actualiza el DNI después de validarlo.
     *
     * @param dni nuevo DNI
     * @throws IllegalArgumentException si el DNI no cumple el formato
     */
    public void setDni(String dni) {
        this.dni = validarDni(dni);
    }

    /**
     * Actualiza el nombre del empleado.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Actualiza el primer apellido.
     *
     * @param apellido1 nuevo primer apellido
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Actualiza el segundo apellido.
     *
     * @param apellido2 nuevo segundo apellido
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Actualiza la dirección postal.
     *
     * @param direccion nueva dirección
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Actualiza el correo después de validarlo.
     *
     * @param mail nueva dirección de correo
     * @throws IllegalArgumentException si el correo no cumple el formato
     */
    public void setMail(String mail) {
        this.mail = validarCorreo(mail);
    }

    /**
     * Actualiza el teléfono después de validarlo.
     *
     * @param telefono nuevo teléfono de contacto
     * @throws IllegalArgumentException si el teléfono no cumple el formato
     */
    public void setTelefono(int telefono) {
        this.telefono = validarTelefono(telefono);
    }

    /**
     * Actualiza la fecha de nacimiento después de validarla.
     *
     * @param fecha nueva fecha de nacimiento
     * @throws IllegalArgumentException si la fecha es nula o futura
     */
    public void setFechaNacimiento(LocalDate fecha) {
        this.fechaNacimiento = validarFechaNac(fecha);
    }

    /**
     * Asigna el empleado a otro departamento.
     *
     * @param departamento nuevo departamento
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Actualiza la fecha de incorporación.
     *
     * @param fecha nueva fecha de ingreso
     */
    public void setFechaIngreso(LocalDate fecha) {

        this.fechaIngreso = fecha;
    }

    /**
     * Devuelve una representación textual de los datos del empleado.
     *
     * @return descripción textual del empleado, sin incluir su contraseña
     */
    @Override
    public String toString() {
        return "Empleado{usuario=" + usuario + ", dni=" + dni + ", nombre=" + nombre
                + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
                + ", direccion=" + direccion + ", mail=" + mail + ", telefono=" + telefono
                + ", fechaNacimiento=" + fechaNacimiento + ", departamento=" + departamento
                + ", fechaIngreso=" + fechaIngreso + '}';
    }
}
