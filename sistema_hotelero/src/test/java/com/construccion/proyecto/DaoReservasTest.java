package com.construccion.proyecto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.construccion.proyecto.dao.DaoReservas;
import com.construccion.proyecto.model.Reservacion;

public class DaoReservasTest {
    private DaoReservas daoReservas;
    private Reservacion reservacion;

    @Before
    public void setUp() throws SQLException {
        daoReservas = new DaoReservas();
        reservacion = new Reservacion(1, 1, 101, LocalDate.now(), LocalDate.now().plusDays(3));
        limpiarBaseDeDatos();
    }

    @After
    public void tearDown() throws SQLException {
        limpiarBaseDeDatos();
    }

    private void limpiarBaseDeDatos() throws SQLException {
        daoReservas.eliminarReservacion(reservacion.getIdReservacion());
    }

    @Test
    public void testAgregarReservacion() throws SQLException {
        boolean resultado = daoReservas.agregarReservacion(reservacion);
        assertTrue("La reservación debería haberse agregado", resultado);

        Reservacion reservacionGuardada = daoReservas.buscarReservacion(reservacion.getIdReservacion());
        assertNotNull("La reservación debería existir", reservacionGuardada);
        assertEquals("El ID de la reservación no coincide", reservacion.getIdReservacion(), reservacionGuardada.getIdReservacion());
    }

    @Test
    public void testEliminarReservacion() throws SQLException {
        daoReservas.agregarReservacion(reservacion);
        boolean eliminado = daoReservas.eliminarReservacion(reservacion.getIdReservacion());
        assertTrue("La reservación debería haberse eliminado", eliminado);

        Reservacion reservacionEliminada = daoReservas.buscarReservacion(reservacion.getIdReservacion());
        assertNull("La reservación eliminada no debería existir", reservacionEliminada);
    }

    @Test
    public void testModificarReservacion() throws SQLException {
        daoReservas.agregarReservacion(reservacion);
        reservacion.setFechaSalida(LocalDate.now().plusDays(5));
        daoReservas.modificarReservas(reservacion);

        Reservacion reservacionModificada = daoReservas.buscarReservacion(reservacion.getIdReservacion());
        assertNotNull("La reservación modificada debería existir", reservacionModificada);
        assertEquals("La fecha de salida no se actualizó correctamente", reservacion.getFechaSalida(), reservacionModificada.getFechaSalida());
    }

    @Test
    public void testObtenerReservaciones() throws SQLException {
        daoReservas.agregarReservacion(reservacion);
        List<Reservacion> reservaciones = daoReservas.obtenerReservaciones();

        assertNotNull("La lista de reservaciones no debería ser nula", reservaciones);
        assertFalse("La lista de reservaciones no debería estar vacía", reservaciones.isEmpty());

        boolean encontrada = reservaciones.stream().anyMatch(r -> r.getIdReservacion() == reservacion.getIdReservacion());
        assertTrue("La reservación debería estar en la lista", encontrada);
    }

    @Test
    public void testObtenerFechasOcupadasPorHabitacion() throws SQLException {
        daoReservas.agregarReservacion(reservacion);
        Map<Integer, List<LocalDate>> fechasOcupadas = daoReservas.obtenerFechasOcupadasPorHabitacion();

        assertNotNull("El mapa de fechas ocupadas no debería ser nulo", fechasOcupadas);
        assertTrue("El mapa debería contener la habitación de la reservación", fechasOcupadas.containsKey(reservacion.getIdHabitacion()));

        List<LocalDate> fechas = fechasOcupadas.get(reservacion.getIdHabitacion());
        assertTrue("Las fechas de llegada deberían estar en la lista", fechas.contains(reservacion.getFechaLlegada()));
        assertTrue("Las fechas de salida deberían estar en la lista", fechas.contains(reservacion.getFechaSalida()));
    }

    @Test
    public void testBuscarReservacion() throws SQLException {
        daoReservas.agregarReservacion(reservacion);
        Reservacion reservacionBuscada = daoReservas.buscarReservacion(reservacion.getIdReservacion());

        assertNotNull("La reservación buscada debería existir", reservacionBuscada);
        assertEquals("El ID de la reservación no coincide", reservacion.getIdReservacion(), reservacionBuscada.getIdReservacion());
    }
}
