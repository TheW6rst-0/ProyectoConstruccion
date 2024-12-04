package com.construccion.proyecto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.construccion.proyecto.model.Habitacion;

public class DaoHabitaciones {
    private Connection con = null;
    private final String host = "jdbc:mysql://localhost:3306/hotel";
    private final String user = "root";
    private final String pass = "";



    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, user, pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            
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
           
            System.err.println("Error al eliminar el Habitacion de la base de datos: " + e.getMessage());
        }
    }

    public void modificarHabitacion(Habitacion habitacion) throws SQLException {
        con = getCon();
        String sql = "UPDATE habitacion SET tipoHabitacion = ?, camas = ?, precio = ?, disponibilidad = ? WHERE idHabitacion = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, habitacion.getTipoHabitacion());
            statement.setString(2, habitacion.getCamas());
            statement.setDouble(3, habitacion.getPrecio());
            statement.setBoolean(4, habitacion.isDisponibilidad());
            statement.setInt(5, habitacion.getIdHabitacion());
    
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Habitación actualizada exitosamente.");
            } else {
                System.out.println("No se encontró ninguna habitación con el ID especificado.");
            }
        } catch (SQLException e) {
          
            System.err.println("Error al modificar la habitación: " + e.getMessage());
        }
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
        
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
        return habitacion;
    }

    public Habitacion buscarHabitacionPorTipo(String tipo) throws SQLException {
        con = getCon();
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
    public List<Habitacion> buscarHabitacionesDisponiblesPorTipoYFechas(String tipo, LocalDate fechaInicio, LocalDate fechaFin) throws SQLException {
        con = getCon(); // Obtener la conexión
        List<Habitacion> habitacionesDisponibles = new ArrayList<>();
        String sql = "SELECT * FROM habitacion h " +
                     "WHERE h.tipoHabitacion = ? " +
                     "AND h.disponibilidad = true " +
                     "AND h.idHabitacion NOT IN ( " +
                     "    SELECT r.idHabitacion " +
                     "    FROM reservaciones r " +
                     "    WHERE (r.fechaLlegada <= ? AND r.fechaSalida >= ?) " +
                     ");";
    
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, tipo);
            statement.setDate(2, java.sql.Date.valueOf(fechaFin));
            statement.setDate(3, java.sql.Date.valueOf(fechaInicio));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Habitacion habitacion = new Habitacion(
                        resultSet.getInt("idHabitacion"),
                        resultSet.getString("tipoHabitacion"),
                        resultSet.getString("camas"),
                        resultSet.getDouble("precio"),
                        resultSet.getBoolean("disponibilidad")
                    );
                    habitacionesDisponibles.add(habitacion);
                }
            }
        } catch (SQLException e) {
          
            System.err.println("Error al buscar habitaciones disponibles: " + e.getMessage());
        }
        return habitacionesDisponibles;
    }
    
    


}
