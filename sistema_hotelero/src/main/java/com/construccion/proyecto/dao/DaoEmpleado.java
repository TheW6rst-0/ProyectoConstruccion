package com.construccion.proyecto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.construccion.proyecto.model.Empleado;

/**
 * Clase que gestiona las operaciones relacionadas con la entidad Empleado en la base de datos.
 * Incluye métodos para agregar, eliminar, modificar, buscar y validar empleados.
 */

public class DaoEmpleado {
    private Connection con = null;
    private final String host = "jdbc:mysql://localhost:3306/hotel";
    private final String user = "root";
    private final String pass = "";
/**
     * Constructor de la clase DaoEmpleado.
     */

    public DaoEmpleado() {
    }
/**
     * Establece una conexión con la base de datos.
     * 
     * @return La conexión a la base de datos.
     */

    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, user, pass);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
           
        }
        return con;
    }
/**
     * Agrega un nuevo empleado a la base de datos.
     * 
     * @param emp El objeto Empleado que se desea agregar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public boolean agregarEmpleado(Empleado emp) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO empleado (claveEmp, nombreEmp, usuario, contrasenia, rol) VALUES(?,?,?,?,?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, emp.getClaveEmp());
            statement.setString(2, emp.getNombre());
            statement.setString(3, emp.getUsuario());
            statement.setString(4, emp.getContrasenia());
            statement.setInt(5, emp.getRol());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
          
            System.err.println("Error al guardar el Empleado en la base de datos: " + e.getMessage());
            return false;
        }
    }
/**
     * Elimina un empleado de la base de datos por su clave.
     * 
     * @param emp El objeto Empleado que se desea eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public boolean eliminarEmpleado(Empleado emp) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM empleado WHERE claveEmp=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, emp.getClaveEmp());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
/**
     * Modifica los datos de un empleado en la base de datos.
     * 
     * @param emp El objeto Empleado con los nuevos datos.
     * @return true si la operación fue exitosa, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public boolean modificarEmpleado(Empleado emp) throws SQLException {
        con = getCon();
        String sql = "UPDATE empleado SET nombreEmp = ?, usuario = ?, contrasenia = ?, rol = ? WHERE claveEmp = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, emp.getNombre());
            statement.setString(2, emp.getUsuario());
            statement.setString(3, emp.getContrasenia());
            statement.setInt(4, emp.getRol());
            statement.setInt(5, emp.getClaveEmp());
    
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Empleado actualizado exitosamente.");
                return true;
            } else {
                System.out.println("No se encontró ningún empleado con la clave especificada.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
/**
     * Busca un empleado en la base de datos por su clave.
     * 
     * @param clave La clave del empleado que se busca.
     * @return El objeto Empleado encontrado o null si no se encuentra.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public Empleado buscarEmpleado(int clave) throws SQLException {
        con = getCon();
        String sqlConsulta = "SELECT * FROM empleado WHERE claveEmp = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, clave);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                int claveEmp = resultSet.getInt("claveEmp");
                String nombre = resultSet.getString("nombreEmp");
                String usuario = resultSet.getString("usuario");
                String contrasenia = resultSet.getString("contrasenia");
                int rol = resultSet.getInt("rol");
                return new Empleado(claveEmp, nombre, usuario, contrasenia, rol);
            } else {
                return null;
            }
        } catch (SQLException e) {
            
            System.err.println("Error al buscar el empleado: " + e.getMessage());
            return null;
        }
    }
/**
     * Obtiene una lista de todos los empleados registrados en la base de datos.
     * 
     * @return Una lista de objetos Empleado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public List<Empleado> obtenerEmpleados() throws SQLException {
        con = getCon();
        List<Empleado> empleados = new ArrayList<>();
        String sqlConsulta = "SELECT * FROM empleado";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int claveEmp = resultSet.getInt("claveEmp");
                String nombre = resultSet.getString("nombreEmp");
                String usuario = resultSet.getString("usuario");
                String contrasenia = resultSet.getString("contrasenia");
                int rol = resultSet.getInt("rol");
                empleados.add(new Empleado(claveEmp, nombre, usuario, contrasenia, rol));   
            }
        } catch (SQLException e) {
        
            System.err.println("Error al obtener los Huespedes: " + e.getMessage());
        }
        return empleados;
    }   
    /**
     * Valida las credenciales de un usuario para el inicio de sesión.
     * 
     * @param usuario El nombre de usuario.
     * @param contrasenia La contraseña del usuario.
     * @return Un objeto Empleado si las credenciales son válidas, o null si no lo son.
     */

    public Empleado validarCredenciales(String usuario, String contrasenia) {
        con = getCon();
        String sql = "SELECT * FROM empleado WHERE usuario = ? AND contrasenia = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, usuario);
            statement.setString(2, contrasenia);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                int claveEmp = resultSet.getInt("claveEmp");
                String nombreEmp = resultSet.getString("nombreEmp");
                int rol = resultSet.getInt("rol");
    
                // Crear y devolver el objeto Empleado
                return new Empleado(claveEmp, nombreEmp, usuario, contrasenia, rol);
            }
        } catch (SQLException e) {
          
            System.err.println("Error al validar las credenciales: " + e.getMessage());
        }
        return null; // Si no se encuentra el usuario
    }
    
    
}
