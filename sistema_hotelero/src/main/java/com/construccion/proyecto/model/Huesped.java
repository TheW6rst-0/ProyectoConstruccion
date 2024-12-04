package com.construccion.proyecto.model;
/**
     * Constructor de la clase Habitacion.
     *
     * @param idHabitacion El ID único de la habitación.
     * @param tipoHabitacion El tipo de habitación (ej. individual, doble, suite, etc.).
     * @param camas El tipo y número de camas en la habitación.
     * @param precio El precio de la habitación por noche.
     * @param disponibilidad La disponibilidad de la habitación (si está disponible o no).
     */

public class Huesped {
    /** El nombre del huésped */
    private String nombre;
    
    /** El ID único del huésped */
    private int idHuesped;
    
    /** El correo electrónico del huésped */
    private String email;
    
    /** El ID de la tarjeta del huésped */
    private int idTarjeta;

    /**
     * Constructor de la clase Huesped.
     *
     * @param id El ID único del huésped.
     * @param nombre El nombre del huésped.
     * @param email El correo electrónico del huésped.
     * @param idTarjeta El ID de la tarjeta asociada al huésped.
     */

    public Huesped( int id,String nombre, String email, int idTarjeta) {
        this.idHuesped = id;
        this.nombre = nombre;
        this.email = email;
        this.idTarjeta = idTarjeta;
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

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }
}
