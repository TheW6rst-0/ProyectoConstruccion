package com.construccion.proyecto.dao;
import java.sql.Connection;
import java.sql.Date;
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
/**
 * Clase DAO encargada de realizar las operaciones sobre la base de datos relacionadas con las reservaciones.
 * Proporciona métodos para agregar, eliminar, modificar, buscar y obtener reservaciones,
 * así como obtener las fechas ocupadas por habitación.
 */

public class DaoReservas {
    private Connection con = null;
    private final String host = "jdbc:mysql://localhost:3306/hotel";
    private final String user = "root";
    private final String pass = "";



    /**
     * Establece una conexión con la base de datos.
     *
     * @return La conexión a la base de datos.
     */

    public Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(host, user, pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            
        }
        return con;
    }
/**
     * Agrega una nueva reservación a la base de datos.
     *
     * @param reservacion La reservación que se va a agregar.
     * @return true si la reservación fue agregada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public boolean agregarReservacion(Reservacion reservacion) throws SQLException {
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
            return true;
        } catch (SQLException e) {
           
            return false;
        }
    }

/**
     * Elimina una reservación de la base de datos.
     *
     * @param reservacion La reservación que se va a eliminar.
     * @return true si la reservación fue eliminada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public boolean eliminarHuesped(Reservacion reservacion) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM reservaciones WHERE idReservacion=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, reservacion.getIdReservacion());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
                return false;
        }
    }
    /**
     * Modifica una reservación existente en la base de datos.
     *
     * @param reservacion La reservación con los nuevos datos.
     * @return true si la reservación fue modificada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public boolean modificarReservas(Reservacion reservacion) throws SQLException {
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
                return true;
            } else {
                System.out.println("No se encontró ninguna reservación con el ID especificado.");
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
            /**
     * Obtiene todas las reservaciones registradas en la base de datos.
     *
     * @return Una lista con todas las reservaciones.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

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

    
/**
     * Busca una reservación en la base de datos por su ID.
     *
     * @param idReservacion El ID de la reservación que se desea buscar.
     * @return La reservación con el ID especificado o null si no se encuentra.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public Reservacion buscarReservacion(int idReservacion) throws SQLException {
        con = getCon();
        Reservacion reservacion = null;
        String sqlConsulta = "SELECT * FROM reservaciones WHERE idReservacion = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, idReservacion);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idReservacion");
                int idHuesped = resultSet.getInt("idHuesped");
                int idHabitacion = resultSet.getInt("idHabitacion");
                Date fechaLlegada = resultSet.getDate("fechaLlegada");
                Date fechaSalida = resultSet.getDate("fechaSalida");
                return new Reservacion(id, idHuesped, idHabitacion, fechaLlegada.toLocalDate(), fechaSalida.toLocalDate());

            } else {
                System.out.println("No se encontró ningún Huesped con la clave: " + idReservacion);
                return null;
            }
        } catch (SQLException e) {
            
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
            return null;
        }
    }
    /**
     * Obtiene las fechas ocupadas por habitación desde la base de datos.
     *
     * @return Un mapa con el ID de la habitación y una lista de fechas ocupadas.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

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
/**
     * Elimina una reservación por su ID.
     *
     * @param idReservacion El ID de la reservación que se desea eliminar.
     * @return true si la reservación fue eliminada exitosamente, false en caso contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

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
