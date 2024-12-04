package com.construccion.proyecto.model;

public class Huesped {
    private String nombre;
    private int idHuesped;
    private String email;
<<<<<<< HEAD
<<<<<<< Updated upstream
    private int idtarjeta;
=======
    private int idTarjeta;

>>>>>>> deff216ee419cb11552f5f81f06fef5090b48499
    public Huesped( int id,String nombre, String email, int idtarjeta) {
        this.idHuesped = id;
        this.nombre = nombre;
        this.email = email;
<<<<<<< HEAD
        this.idtarjeta = idtarjeta;
=======
    private int idTarjeta;

    public Huesped( int idHuesped,String nombre, String email, int idTarjeta) {
        this.idHuesped = idHuesped;
        this.nombre = nombre;
        this.email = email;
        this.idTarjeta = idTarjeta;
>>>>>>> Stashed changes
=======
        this.idTarjeta = idtarjeta;
>>>>>>> deff216ee419cb11552f5f81f06fef5090b48499
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

<<<<<<< HEAD
<<<<<<< Updated upstream
    public void setIdhuesped(int idhuesped) {
        this.idhuesped = idhuesped;
=======
    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
>>>>>>> Stashed changes
=======
    public void setIdHuesped(int idhuesped) {
        this.idHuesped = idhuesped;
>>>>>>> deff216ee419cb11552f5f81f06fef5090b48499
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

<<<<<<< HEAD
<<<<<<< Updated upstream
    public void setIdtarjeta(int idtarjeta) {
        this.idtarjeta = idtarjeta;
=======
    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
>>>>>>> Stashed changes
=======
    public void setIdTarjeta(int idtarjeta) {
        this.idTarjeta = idtarjeta;
>>>>>>> deff216ee419cb11552f5f81f06fef5090b48499
    }
}
