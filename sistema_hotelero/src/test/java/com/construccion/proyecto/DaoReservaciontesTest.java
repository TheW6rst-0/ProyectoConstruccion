package com.construccion.proyecto;

import java.time.LocalDate;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.construccion.proyecto.dao.DaoReservas;
import com.construccion.proyecto.model.Reservacion;

public class DaoReservaciontesTest {
    DaoReservas daoReservas = new DaoReservas();

    @Test
    public void testAgregarReservacion() {
        try {
            Reservacion reservacion = new Reservacion(70, 9, 64, LocalDate.now(), LocalDate.now().plusDays(3));
            Boolean estado = daoReservas.agregarReservacion(reservacion);
            assertTrue(estado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testBuscarReservacion() {
        try {
            Reservacion reservacionEncontrada = daoReservas.buscarReservacion(1);
            
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }

    @Test
    public void testEliminarReservacion() {
        try {
            Reservacion reservacion = new Reservacion(70, 9, 64, LocalDate.now(), LocalDate.now().plusDays(3));
            daoReservas.agregarReservacion(reservacion);
            Boolean estado = daoReservas.eliminarReservacion(reservacion.getIdReservacion());
            assertTrue(estado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testModificarReservacion(){
        try{
            Reservacion reservacion = new Reservacion(3, 1, 1, LocalDate.now(), LocalDate.now().plusDays(3));
            daoReservas.agregarReservacion(reservacion);
            Reservacion reservacionModificada = new Reservacion(3, 1, 1, LocalDate.now(), LocalDate.now().plusDays(5));
            Boolean estado = daoReservas.modificarReservas(reservacionModificada);
            Reservacion reservacionEncontrada =daoReservas.buscarReservacion(reservacionModificada.getIdReservacion());
            assertSame(reservacionModificada.getIdHabitacion(), reservacionEncontrada.getIdHabitacion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
