package DAO;
import java.sql.*;

import Model.Habitacion;

public class DaoHabitaciones {
    private Connection con = null;
    private String host = "jdbc:mysql://localhost:3306/hotel";
    private String user = "root";
    private String pass = "";



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

    public void agregarHabitacioens(Habitacion habitacion) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO habitacion (idHabitacion, tipoHabitacion, capacidad, precio, disponibilidad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, habitacion.getIdHabitacion());
            statement.setString(2, habitacion.getTipoHabitacion());
            statement.setString(3, habitacion.getCapacidad());
            statement.setDouble(4, habitacion.getPrecio());
            statement.setBoolean(5, habitacion.isDisponibilidad());


            statement.executeUpdate();

            System.out.println("Habitación guardada exitosamente en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al guardar la Habitación en la base de datos: " + e.getMessage());
        }
    }


    public void eliminarHabitacion(Habitacion habitacion) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM habitacion WHERE idHabitacion=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, habitacion.getIdHabitacion());
            statement.executeUpdate();
            System.out.println("Habitacion eliminado exitosamente de la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar el Habitacion de la base de datos: " + e.getMessage());
        }
    }

    public void modificarHuesped() throws SQLException {
        con = getCon();

    }

    public void buscarHabitacion(int idHabitacion) throws SQLException {
        con = getCon();
        String sqlConsulta = "SELECT * FROM habitacion WHERE idHabitacion = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, idHabitacion);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                int id = resultSet.getInt("idHabitacion");
                String tipoHabitacion = resultSet.getString("tipoHabitacion");
                String capacidad = resultSet.getString("capacidad");
                double precio = resultSet.getDouble("precio");
                boolean disponibilidad = resultSet.getBoolean("disponibilidad");

                System.out.println("Información de la Habitacion:");
                System.out.println("ID Huesped: " + id);
                System.out.println("Nombre: " + tipoHabitacion);
                System.out.println("Email: " + capacidad);
                System.out.println("ID Tarjeta: " + precio);
                System.out.println("Disponibilidad: " + disponibilidad);
            } else {
                System.out.println("No se encontró ningún Huesped con la clave: " + idHabitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
    }
}
