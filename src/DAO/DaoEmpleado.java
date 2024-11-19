package DAO;
import Model.Empleado;

import java.sql.*;
import java.util.ArrayList;

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

    public void modificarEmpleado() throws SQLException {
        con = getCon();

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
}
