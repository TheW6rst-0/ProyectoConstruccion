package com.construccion.proyecto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.construccion.proyecto.model.Tarjeta;

public class DaoTarjeta {
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
            e.printStackTrace();
            System.err.println("Error al guardar el Tarjeta en la base de datos: " + e.getMessage());
        }
    }


    public void eliminarTarjeta(Tarjeta tarjeta) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM tarjeta WHERE idTarjeta=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, tarjeta.getIdTarjeta());
            statement.executeUpdate();
            System.out.println("Tarjeta eliminado exitosamente de la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al Tarjeta el Huesped de la base de datos: " + e.getMessage());
        }
    }

    public void modificarTarjeta() throws SQLException {
        con = getCon();

    }

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
            e.printStackTrace();
            System.err.println("Error al buscar la tarjeta: " + e.getMessage());
        }
        return null; // Retornar null si no se encontró la tarjeta
    }
    
}

