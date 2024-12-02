package com.construccion.proyecto.model;

public class Huesped {
    private String nombre;
    private int idhuesped;
    private String email;
    private int idtarjeta;
    public Huesped( int id,String nombre, String email, int idtarjeta) {
        this.idhuesped = id;
        this.nombre = nombre;
        this.email = email;
        this.idtarjeta = idtarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdhuesped() {
        return idhuesped;
    }

    public void setIdhuesped(int idhuesped) {
        this.idhuesped = idhuesped;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdtarjeta() {
        return idtarjeta;
    }

    public void setIdtarjeta(int idtarjeta) {
        this.idtarjeta = idtarjeta;
    }
}
