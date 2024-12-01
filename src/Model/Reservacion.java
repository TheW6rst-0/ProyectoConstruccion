package Model;

import java.time.LocalDate;

public class Reservacion {
    private int idReservacion;
    private int idHuesped;
    private int idHabitacion;
    private LocalDate fechaLlegada; // Cambiado a LocalDate
    private LocalDate fechaSalida;  // Cambiado a LocalDate

    // Constructor actualizado
    public Reservacion(int idReservacion, int idHuesped, int idHabitacion, LocalDate fechaLlegada, LocalDate fechaSalida) {
        this.idReservacion = idReservacion;
        this.idHuesped = idHuesped;
        this.idHabitacion = idHabitacion;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
    }

    // Getters y Setters
    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
