package com.construccion.proyecto.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.construccion.proyecto.model.Reservacion;

public class DaoReservas {
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

    public void agregarReservacion(Reservacion reservacion) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO reservaciones (idReservacion, idHuesped, idHabitacion, fechaLlegada, fechaSalida) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, reservacion.getIdReservacion());
            statement.setInt(2, reservacion.getIdHuesped());
            statement.setInt(3, reservacion.getIdHabitacion());

            // Convertir LocalDate a java.sql.Date para la base de datos
            statement.setDate(4, java.sql.Date.valueOf(reservacion.getFechaLlegada()));
            statement.setDate(5, java.sql.Date.valueOf(reservacion.getFechaSalida()));

            statement.executeUpdate();
            System.out.println("Reservación guardada exitosamente en la base de datos.");
        } catch (SQLException e) {
           
            System.err.println("Error al guardar la reservación en la base de datos: " + e.getMessage());
        }
    }


    public void eliminarHuesped(Reservacion reservacion) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM reservaciones WHERE idReservacion=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, reservacion.getIdReservacion());
            statement.executeUpdate();
            System.out.println("Reservacion eliminado exitosamente de la base de datos.");
        } catch (SQLException e) {
            
            System.err.println("Error al eliminar el Reservacion de la base de datos: " + e.getMessage());
        }
    }

    public void modificarReservas(Reservacion reservacion) throws SQLException {
        con = getCon();
        String sql = "UPDATE reservaciones SET idHuesped = ?, idHabitacion = ?, fechaLlegada = ?, fechaSalida = ? WHERE idReservacion = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, reservacion.getIdHuesped());
            statement.setInt(2, reservacion.getIdHabitacion());
    
            // Convertir LocalDate a java.sql.Date para la base de datos
            statement.setDate(3, java.sql.Date.valueOf(reservacion.getFechaLlegada()));
            statement.setDate(4, java.sql.Date.valueOf(reservacion.getFechaSalida()));
    
            statement.setInt(5, reservacion.getIdReservacion());
    
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Reservación actualizada exitosamente.");
            } else {
                System.out.println("No se encontró ninguna reservación con el ID especificado.");
            }
        } catch (SQLException e) {
            
            System.err.println("Error al modificar la reservación: " + e.getMessage());
        }
    }
        
    public List<Reservacion> obtenerReservaciones() throws SQLException {
        con = getCon();
        List<Reservacion> reservaciones = new ArrayList<>();
        String sqlConsulta = "SELECT * FROM reservaciones";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idReservacion = resultSet.getInt("idReservacion");
                int idHuesped = resultSet.getInt("idHuesped");
                int idHabitacion = resultSet.getInt("idHabitacion");
                Date fechaLlegada = resultSet.getDate("fechaLlegada");
                Date fechaSalida = resultSet.getDate("fechaSalida");
                reservaciones.add(new Reservacion(idReservacion, idHuesped, idHabitacion, fechaLlegada.toLocalDate(), fechaSalida.toLocalDate())); 
            }
        } catch (SQLException e) {
           
            System.err.println("Error al obtener los Huespedes: " + e.getMessage());
        }
        return reservaciones;
    }   

    

    public void buscarReservacion(int idReservacion) throws SQLException {
        con = getCon();
        String sqlConsulta = "SELECT * FROM reservaciones WHERE idReservacion = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, idReservacion);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idReservacion");



                System.out.println("Información del Reservacion:");
                System.out.println("ID Huesped: " + id);

            } else {
                System.out.println("No se encontró ningún Huesped con la clave: " + idReservacion);
            }
        } catch (SQLException e) {
            
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
    }

}
