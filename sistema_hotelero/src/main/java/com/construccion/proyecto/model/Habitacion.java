package com.construccion.proyecto.model;
/**
 * Representa una habitación en el sistema, con atributos como ID, tipo, camas, 
 * precio y disponibilidad. Permite acceder y modificar estos atributos a través 
 * de los métodos proporcionados.
 */

public class Habitacion {
        /** El ID único de la habitación */
        private int idHabitacion;
    
        /** El tipo de la habitación (ej. individual, doble, suite, etc.) */
        private String tipoHabitacion;
        
        /** El tipo y número de camas en la habitación */
        private String camas;
        
        /** El precio de la habitación por noche */
        private double precio;
        
        /** La disponibilidad de la habitación (si está disponible o no) */
        private boolean disponibilidad;
/**
     * Constructor de la clase Habitacion.
     *
     * @param idHabitacion El ID único de la habitación.
     * @param tipoHabitacion El tipo de habitación (ej. individual, doble, suite, etc.).
     * @param camas El tipo y número de camas en la habitación.
     * @param precio El precio de la habitación por noche.
     * @param disponibilidad La disponibilidad de la habitación (si está disponible o no).
     */
    
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
