package com.construccion.proyecto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import com.construccion.proyecto.dao.DaoHabitaciones;
import com.construccion.proyecto.model.Habitacion;

public class DaoHabitacionesTest {
    private DaoHabitaciones daoHabitaciones;
    private Habitacion habitacion;

    @Before
    public void setUp() {
        daoHabitaciones = new DaoHabitaciones();
        habitacion = new Habitacion(1, "Suite","3", 100.0, true);
    }

    @Test
    public void testAgregarHabitacion() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        Habitacion habitacionGuardada = daoHabitaciones.buscarHabitacion(habitacion.getIdHabitacion());
        assertNotNull(habitacionGuardada);
        assertEquals(habitacion.getTipoHabitacion(), habitacionGuardada.getTipoHabitacion());
    }

    @Test
    public void testEliminarHabitacion() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        daoHabitaciones.eliminarHabitacion(habitacion);
        Habitacion habitacionEliminada = daoHabitaciones.buscarHabitacion(habitacion.getIdHabitacion());
        assertNull(habitacionEliminada);
    }

    @Test
    public void testModificarHabitacion() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        habitacion.setTipoHabitacion("Suite");
        daoHabitaciones.modificarHabitacion(habitacion);
        Habitacion habitacionActualizada = daoHabitaciones.buscarHabitacion(habitacion.getIdHabitacion());
        assertNotNull(habitacionActualizada);
        assertEquals("Suite", habitacionActualizada.getTipoHabitacion());
    }

    @Test
    public void testBuscarHabitacion() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        Habitacion habitacionObtenida = daoHabitaciones.buscarHabitacion(habitacion.getIdHabitacion());
        assertNotNull(habitacionObtenida);
        assertEquals(habitacion.getIdHabitacion(), habitacionObtenida.getIdHabitacion());
    }

    @Test
    public void testBuscarHabitacionPorTipo() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        Habitacion habitacionObtenida = daoHabitaciones.buscarHabitacionPorTipo(habitacion.getTipoHabitacion());
        assertEquals(habitacion.getTipoHabitacion(), habitacionObtenida.getTipoHabitacion());
    }

    @Test
    public void testBuscarHabitacionesDisponiblesPorTipoYFechas() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        List<Habitacion> habitacionesDisponibles = daoHabitaciones.buscarHabitacionesDisponiblesPorTipoYFechas(
                habitacion.getTipoHabitacion(), LocalDate.now(), LocalDate.now().plusDays(1));
        assertNotNull(habitacionesDisponibles);
    }

    @Test
    public void testGetHabitacionesPorTipo() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        List<Integer> ids = daoHabitaciones.getHabitacionesPorTipo(habitacion.getTipoHabitacion());
        assertNotNull(ids);
        assertTrue(ids.contains(habitacion.getIdHabitacion()));
    }


    @Test
    public void testActualizarDisponibilidad() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        daoHabitaciones.actualizarDisponibilidad(habitacion.getIdHabitacion(), false);
        boolean disponibilidadActualizada = daoHabitaciones.getDisponibilidadPorId(habitacion.getIdHabitacion());
        assertFalse(disponibilidadActualizada);
    }

    @Test
    public void testGetAllHabitaciones() throws SQLException {
        daoHabitaciones.agregarHabitaciones(habitacion);
        List<Integer> ids = daoHabitaciones.getAllHabitaciones();
        assertNotNull(ids);
        assertTrue(ids.size() > 0);
    }
}