package com.construccion.proyecto.model;

public class Administrador extends Empleado {
    public Administrador(int clave,String nombre, String usuario, String contrasenia, int rol) {
        super(clave,nombre, usuario, contrasenia, rol);
        this.claveEmp=clave;
        this.usuario=usuario;
        this.nombre=nombre;
        this.contrasenia=contrasenia;
        this.rol=rol;
    }

}
