package com.construccion.proyecto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.construccion.proyecto.model.Reservacion;

public class DaoReservas {
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            System.err.println("Error al modificar la reservación: " + e.getMessage());
        }
    }
    

    public void buscarReservacion(int idReservacion) throws SQLException {
        con = getCon();
        String sqlConsulta = "SELECT * FROM reservaciones WHERE idResercion = ?";
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
            e.printStackTrace();
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
    }

    public Map<Integer, List<LocalDate>> obtenerFechasOcupadasPorHabitacion() throws SQLException {
    String sql = "SELECT idHabitacion, fechaLlegada, fechaSalida FROM reservaciones";
    con = getCon();
    Map<Integer, List<LocalDate>> fechasOcupadas = new HashMap<>();
    try (PreparedStatement statement = con.prepareStatement(sql)) {
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            int idHabitacion = rs.getInt("idHabitacion");
            LocalDate fechaLlegada = rs.getDate("fechaLlegada").toLocalDate();
            LocalDate fechaSalida = rs.getDate("fechaSalida").toLocalDate();

            fechasOcupadas.putIfAbsent(idHabitacion, new ArrayList<>());
            fechasOcupadas.get(idHabitacion).add(fechaLlegada);
            fechasOcupadas.get(idHabitacion).add(fechaSalida);  // Otras fechas de ocupación
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al obtener las fechas ocupadas: " + e.getMessage());
    }
    return fechasOcupadas;
}

public boolean eliminarReservacion(int idReservacion) throws SQLException {
    con = getCon();
    String sql = "DELETE FROM reservaciones WHERE idReservacion = ?";
    try (PreparedStatement statement = con.prepareStatement(sql)) {
        statement.setInt(1, idReservacion);
        int rowsAffected = statement.executeUpdate();
        
        return rowsAffected > 0; // Retornamos true si se eliminó alguna fila
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al eliminar la reservación: " + e.getMessage());
    }
}


}
