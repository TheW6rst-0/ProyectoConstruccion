package com.construccion.proyecto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import com.construccion.proyecto.dao.DaoHuesped;
import com.construccion.proyecto.model.Huesped;

public class DaoHuespedTest {
    DaoHuesped daoHuesped = new DaoHuesped();  

    @Test
    public void obtenerHuespedesTest() {
        try {
            assertNotNull(daoHuesped.obtenerHuespedes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void agregarHuespedTest() {
        Huesped huesped = new Huesped( 3, "Alexander", "alexander@gmail.com", 235);
        try {
            daoHuesped.agregarHuesped(huesped);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarHuespedTest() {
        Huesped huesped = new Huesped( 3, "Alex", "alexander@gmail.com", 235);
        try {
            Boolean fueEliminado = daoHuesped.eliminarHuesped(huesped);
            assertEquals(true, fueEliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void modificarHuespedTest() {
        Huesped huesped = new Huesped( 3, "Alexander", "alexander@gmail.com", 235);
        huesped.setNombre("Alex");
        try {
            daoHuesped.modificarHuesped(huesped);
            Huesped huespedModificado = daoHuesped.buscarHuesped(huesped.getIdHuesped());
            assertEquals("Alex", huespedModificado.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buscarHuespedPorIdTest() {
        try {
            Huesped huesped = daoHuesped.buscarHuesped(2);
            assertNotNull(huesped);
            assertEquals(2, huesped.getIdHuesped());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void buscarHuespedPorNombreTest() {
        try {
            Huesped huesped = daoHuesped.buscarHuesped("Skibidi");
            assertNotNull(huesped);
            assertEquals("Skibidi", huesped.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
