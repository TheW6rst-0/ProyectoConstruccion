package com.construccion.proyecto.dao;
import java.sql.*;

import com.construccion.proyecto.model.Habitacion;

public class DaoHabitaciones {
    private Connection con = null;
    private String host = "jdbc:mysql://localhost:3306/hotel";
    private String user = "root";
    private String pass = "";



    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, user, pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void agregarHabitacioens(Habitacion habitacion) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO habitacion (idHabitacion, tipoHabitacion, camas, precio, disponibilidad) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, habitacion.getIdHabitacion());
            statement.setString(2, habitacion.getTipoHabitacion());
            statement.setString(3, habitacion.getCamas());
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

    public Habitacion buscarHabitacion(int idHabitacion) throws SQLException {
        con = getCon();
        Habitacion habitacion = null;
        String sqlConsulta = "SELECT * FROM habitacion WHERE idHabitacion = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, idHabitacion);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                int id = resultSet.getInt("idHabitacion");
                String tipoHabitacion = resultSet.getString("tipoHabitacion");
                String camas = resultSet.getString("camas");
                double precio = resultSet.getDouble("precio");
                boolean disponibilidad = resultSet.getBoolean("disponibilidad");
                habitacion = new Habitacion(id, tipoHabitacion, camas, precio, disponibilidad);
            } else {
                System.out.println("No se encontró ningún Huesped con la clave: " + idHabitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
        return habitacion;
    }

    public Habitacion buscarHabitacionPorTipo(String tipo) throws SQLException {
        String sql = "SELECT * FROM habitacion WHERE tipoHabitacion = ? LIMIT 1";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, tipo);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                return new Habitacion(
                    resultSet.getInt("idHabitacion"),
                    resultSet.getString("tipoHabitacion"),
                    resultSet.getString("camas"),
                    resultSet.getDouble("precio"),
                    resultSet.getBoolean("disponibilidad")
                );
            }
        }
        return null;
    }
    

}
