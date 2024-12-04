package com.construccion.proyecto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.construccion.proyecto.model.Tarjeta;
/**
 * Clase DAO encargada de realizar las operaciones sobre la base de datos relacionadas con las tarjetas.
 * Proporciona métodos para agregar, eliminar, modificar y buscar tarjetas.
 */

public class DaoTarjeta {
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
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
           
        }
        return con;
    }
    /**
     * Agrega una nueva tarjeta a la base de datos.
     *
     * @param tarjeta La tarjeta que se va a agregar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public void agregarTarjeta(Tarjeta tarjeta) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO tarjeta (idTarjeta, nombreTitular, numeroTarjeta, nip, vencimiento, saldo) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, tarjeta.getIdTarjeta());
            statement.setString(2, tarjeta.getNombreTitular());
            statement.setString(3, tarjeta.getNumeroTarjeta());
            statement.setString(4, tarjeta.getNip());

            // Convertir la fecha a java.sql.Date
            java.sql.Date fechaVencimiento = new java.sql.Date(tarjeta.getVencimiento().getTime());
            statement.setDate(5, fechaVencimiento);

            statement.setDouble(6, tarjeta.getSaldo());
            statement.executeUpdate();
            System.out.println("Tarjeta guardado exitosamente en la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al guardar el Tarjeta en la base de datos");
        }
    }

    /**
     * Elimina una tarjeta de la base de datos.
     *
     * @param tarjeta La tarjeta que se va a eliminar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public void eliminarTarjeta(Tarjeta tarjeta) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM tarjeta WHERE idTarjeta=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, tarjeta.getIdTarjeta());
            statement.executeUpdate();
            System.out.println("Tarjeta eliminado exitosamente de la base de datos.");
        } catch (SQLException e) {
           
            System.err.println("Error al Tarjeta el Huesped de la base de datos: " + e.getMessage());
        }
    }
/**
     * Modifica una tarjeta existente en la base de datos.
     * (Este método no está implementado en la clase actual).
     *
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */

    public void modificarTarjeta() throws SQLException {
        con = getCon();

    }
/**
     * Busca una tarjeta en la base de datos por su ID.
     *
     * @param idTarjeta El ID de la tarjeta que se desea buscar.
     * @return La tarjeta con el ID especificado o null si no se encuentra.
     */

    public Tarjeta buscarTarjeta(int idTarjeta) {
        con = getCon();
        String sqlConsulta = "SELECT * FROM tarjeta WHERE idTarjeta = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, idTarjeta);
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                int id = resultSet.getInt("idTarjeta");
                String nombreTitular = resultSet.getString("nombreTitular");
                String numeroTarjeta = resultSet.getString("numeroTarjeta");
                String nip = resultSet.getString("nip");
                Date vencimiento = resultSet.getDate("vencimiento"); // Convertir a tipo Date
                double saldo = resultSet.getDouble("saldo");
    
                // Crear y devolver la tarjeta encontrada
                return new Tarjeta(id, nombreTitular, numeroTarjeta, nip, vencimiento, saldo);
            } else {
                System.out.println("No se encontró ninguna tarjeta con el ID: " + idTarjeta);
            }
        } catch (SQLException e) {
           
            System.err.println("Error al buscar la tarjeta: " + e.getMessage());
        }
        return null; // Retornar null si no se encontró la tarjeta
    }
    
}

