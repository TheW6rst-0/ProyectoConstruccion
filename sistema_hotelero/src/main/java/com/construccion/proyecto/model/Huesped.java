package com.construccion.proyecto.model;

public class Huesped {
    private String nombre;
    private int idhuesped;
    private String email;
<<<<<<< Updated upstream
    private int idtarjeta;
    public Huesped( int id,String nombre, String email, int idtarjeta) {
        this.idhuesped = id;
        this.nombre = nombre;
        this.email = email;
        this.idtarjeta = idtarjeta;
=======
    private int idTarjeta;

    public Huesped( int idHuesped,String nombre, String email, int idTarjeta) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.email = email;
        this.idTarjeta = idTarjeta;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public void setIdhuesped(int idhuesped) {
        this.idhuesped = idhuesped;
=======
    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public void setIdtarjeta(int idtarjeta) {
        this.idtarjeta = idtarjeta;
=======
    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
>>>>>>> Stashed changes
    }
}
