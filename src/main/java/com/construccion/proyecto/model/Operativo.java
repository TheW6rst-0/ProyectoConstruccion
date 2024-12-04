package com.construccion.proyecto.model;

public class Operativo extends Empleado{
    public Operativo( int clave, String nombre, String usuario, String contrasenia, int rol) {
        super( clave,nombre, usuario, contrasenia, rol);
        this.claveEmp=clave;
        this.usuario=usuario;
        this.nombre=nombre;
        this.contrasenia=contrasenia;
        this.rol=rol;
    }
}
