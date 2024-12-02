package com.construccion.proyecto.model;

public class Habitacion {
    private int idHabitacion;
    private String tipoHabitacion;
    private String camas;
    private double precio;
    private boolean disponibilidad;

    public Habitacion(int idHabitacion, String tipoHabitacion, String camas, double precio, boolean disponibilidad) {
        this.idHabitacion = idHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.camas = camas;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public String getCamas() {
        return camas;
    }

    public void setCamas(String camas) {
        this.camas = camas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
