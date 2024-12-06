package com.construccion.proyecto.model;
/**
 * Representa a un empleado dentro del sistema, con atributos como clave, nombre, 
 * usuario, contraseña y rol. Esta clase también permite gestionar reservas a 
 * través de los métodos de registro y cancelación, aunque estos métodos actualmente 
 * no implementan ninguna funcionalidad.
 */

public class Empleado {
        /** La clave única que identifica al empleado */
    private int claveEmp;
        /** El nombre del empleado */
    private String nombre;
        /** El nombre de usuario del empleado */
    private String usuario;
        /** La contraseña del empleado */
    private String contrasenia;
    /** El rol del empleado (por ejemplo, administrador, recepcionista, etc.) */
    private int rol;
    /**
     * Constructor de la clase Empleado.
     *
     * @param claveEmp La clave única del empleado.
     * @param nombre El nombre del empleado.
     * @param usuario El nombre de usuario del empleado.
     * @param contrasenia La contraseña del empleado.
     * @param rol El rol del empleado.
     */

    public Empleado( int claveEmp, String nombre, String usuario, String contrasenia, int rol) {
        this.claveEmp = claveEmp;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Empleado(String nombre, String usuario, String contrasenia, int rol){
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getClaveEmp() {
        return claveEmp;
    }

    public void setClaveEmp(int claveEmp) {
        this.claveEmp = claveEmp;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRol(){
        return rol;
    }
}
