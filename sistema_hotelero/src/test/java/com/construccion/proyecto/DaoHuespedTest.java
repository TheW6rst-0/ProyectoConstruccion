package com.construccion.proyecto;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.construccion.proyecto.dao.DaoHuesped;
import com.construccion.proyecto.model.Huesped;

public class DaoHuespedTest {
    DaoHuesped daoHuesped = new DaoHuesped();  

    @Test
    public void obtenerHuespedesTest() {
        List<Huesped> huespedes = null;
        try {
            huespedes = daoHuesped.obtenerHuespedes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Huesped huespedEsperado = new Huesped(2, "Skibidi", "meperdonas¿@gmail.com", 235);
        Huesped huespedRecibido = huespedes.get(0);
        assertEquals(huespedEsperado.getNombre(),huespedRecibido.getNombre());
        assertEquals(huespedEsperado.getIdHuesped(),huespedRecibido.getIdHuesped());
        assertEquals(huespedEsperado.getEmail(),huespedRecibido.getEmail());
        assertEquals(huespedEsperado.getIdTarjeta(),huespedRecibido.getIdTarjeta());
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
}
