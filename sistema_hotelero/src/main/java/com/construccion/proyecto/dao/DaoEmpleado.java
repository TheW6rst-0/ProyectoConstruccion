package com.construccion.proyecto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.construccion.proyecto.model.Empleado;


public class DaoEmpleado {
    private Connection con = null;
    private String host = "jdbc:mysql://localhost:3306/hotel";
    private String user = "root";
    private String pass = "";

    public DaoEmpleado() {
    }

    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, user, pass);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void agregarEmpleado(Empleado emp) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO empleado (claveEmp, nombreEmp, usuario, contrasenia) VALUES(?,?,?,?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, emp.getClaveEmp());
            statement.setString(2, emp.getNombre());
            statement.setString(3, emp.getUsuario());
            statement.setString(4, emp.getContrasenia());
            statement.executeUpdate();
            System.out.println("Empleado guardado exitosamente en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al guardar el Empleado en la base de datos: " + e.getMessage());
        }
    }

    public void eliminarEmpleado(Empleado emp) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM empleado WHERE claveEmp=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, emp.getClaveEmp());
            statement.executeUpdate();
            System.out.println("Empleado eliminado exitosamente de la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar el Empleado de la base de datos: " + e.getMessage());
        }
    }

    public void modificarEmpleado(Empleado emp) throws SQLException {
        con = getCon();
        String sql = "UPDATE empleado SET nombreEmp = ?, usuario = ?, contrasenia = ? WHERE claveEmp = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, emp.getNombre());
            statement.setString(2, emp.getUsuario());
            statement.setString(3, emp.getContrasenia());
            statement.setInt(4, emp.getClaveEmp());
    
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Empleado actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún empleado con la clave especificada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al modificar el empleado: " + e.getMessage());
        }
    }

    public void buscarEmpleado(int clave) throws SQLException {
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

                System.out.println("Información del empleado:");
                System.out.println("Clave: " + claveEmp);
                System.out.println("Nombre: " + nombre);
                System.out.println("Usuario: " + usuario);
                System.out.println("Contraseña: " + contrasenia);
            } else {
                System.out.println("No se encontró ningún empleado con la clave: " + clave);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar el empleado: " + e.getMessage());
        }
    }

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
            e.printStackTrace();
            System.err.println("Error al validar las credenciales: " + e.getMessage());
        }
        return null; // Si no se encuentra el usuario
    }
    
    
}
