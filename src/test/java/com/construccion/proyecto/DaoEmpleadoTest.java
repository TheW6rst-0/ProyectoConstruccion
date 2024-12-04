package com.construccion.proyecto;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.construccion.proyecto.dao.DaoEmpleado;
import com.construccion.proyecto.model.Empleado;

public class DaoEmpleadoTest {
    DaoEmpleado daoEmpleado = new DaoEmpleado();
    List<Empleado> empleados;

    @Test
    public void testObtenerEmpleados() {
        try {
            empleados = daoEmpleado.obtenerEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(empleados);
    }

    @Test
    public void testAgregarEmpleado() {
        Empleado emp = new Empleado(1, "John Doe", "jdoe", "password", 1);
        boolean result = false;
        try {
            result = daoEmpleado.agregarEmpleado(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test
    public void testEliminarEmpleado() {
        Empleado emp = new Empleado(1, "John Doe", "jdoe", "password", 1);
        boolean result = false;
        try {
            result = daoEmpleado.eliminarEmpleado(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test
    public void testModificarEmpleado() {
        Empleado emp = new Empleado(1, "John Doe", "jdoe", "password", 1);
        boolean result = false;
        try {
            result = daoEmpleado.modificarEmpleado(emp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(result);
    }

    @Test
    public void testBuscarEmpleado() {
        Empleado emp = null;
        try {
            emp = daoEmpleado.buscarEmpleado(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(emp);
        assertEquals(1, emp.getClaveEmp());
        assertEquals("John Doe", emp.getNombre());
        assertEquals("jdoe", emp.getUsuario());
        assertEquals("password", emp.getContrasenia());
        assertEquals(1, emp.getRol());
    }

    @Test
    public void testValidarCredenciales() {
        Empleado emp = null;
        try {
            emp = daoEmpleado.validarCredenciales("jdoe", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(emp);
        assertEquals(1, emp.getClaveEmp());
        assertEquals("John Doe", emp.getNombre());
        assertEquals("jdoe", emp.getUsuario());
        assertEquals("password", emp.getContrasenia());
        assertEquals(1, emp.getRol());
    }

    @Test
    public void testBuscarEmpleadoNoExistente() {
        Empleado emp = null;
        try {
            emp = daoEmpleado.buscarEmpleado(999);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(emp);
    }

    @Test
    public void testValidarCredencialesIncorrectas() {
        Empleado emp = null;
        try {
            emp = daoEmpleado.validarCredenciales("wronguser", "wrongpassword");
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(emp);
    }
}

