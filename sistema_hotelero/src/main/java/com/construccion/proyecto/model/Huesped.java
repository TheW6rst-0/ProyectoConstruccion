package com.construccion.proyecto.model;

public class Huesped {
    private String nombre;
    private int idHuesped;
    private String email;
    private int idTarjeta;

    public Huesped( int id,String nombre, String email, int idtarjeta) {
        this.idHuesped = id;
        this.nombre = nombre;
        this.email = email;
        this.idTarjeta = idtarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idhuesped) {
        this.idHuesped = idhuesped;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idtarjeta) {
        this.idTarjeta = idtarjeta;
    }
}
