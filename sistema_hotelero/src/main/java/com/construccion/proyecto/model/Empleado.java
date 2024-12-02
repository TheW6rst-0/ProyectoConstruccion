package com.construccion.proyecto.model;

public class Empleado {
    protected int claveEmp;
    protected String nombre;
    protected String usuario;
    protected String contrasenia;
    protected int rol;

    public Empleado( int clave, String nombre, String usuario, String contrasenia, int rol) {
        this.claveEmp = clave;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public void registrarReserva(){
    }

    public void cancelarReserva(){

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
