package com.construccion.proyecto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.construccion.proyecto.model.Huesped;
public class DaoHuesped {
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

    public void agregarHuesped(Huesped huesped) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO huesped (idHuesped,nombreHuesped,emailHuesped,idTarjeta) VALUES(?,?,?,?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, huesped.getIdhuesped());
            statement.setString(2, huesped.getNombre());
            statement.setString(3, huesped.getEmail());
            statement.setInt(4, huesped.getIdtarjeta());
            statement.executeUpdate();
            System.out.println("Huesped guardado exitosamente en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al guardar el Huesped en la base de datos: " + e.getMessage());
        }
    }

    public void eliminarHuesped(Huesped huesped) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM huesped WHERE idHuesped=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, huesped.getIdhuesped());
            statement.executeUpdate();
            System.out.println("Huesped eliminado exitosamente de la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al eliminar el Huesped de la base de datos: " + e.getMessage());
        }
    }

    public void modificarHuesped(Huesped huesped) throws SQLException {
        con = getCon();
        String sql = "UPDATE huesped SET nombreHuesped = ?, emailHuesped = ?, idTarjeta = ? WHERE idHuesped = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getEmail());
            statement.setInt(3, huesped.getIdtarjeta());
            statement.setInt(4, huesped.getIdhuesped());
    
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Huésped actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún huésped con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al modificar el huésped: " + e.getMessage());
        }
    }
    

    // public void buscarHuesped(int idHuesped) throws SQLException {
    //     con = getCon();
    //     String sqlConsulta = "SELECT * FROM huesped WHERE idHuesped = ?";
    //     try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
    //         statement.setInt(1, idHuesped);
    //         ResultSet resultSet = statement.executeQuery();


    //         if (resultSet.next()) {
    //             int id = resultSet.getInt("idHuesped");
    //             String nombreHuesped = resultSet.getString("nombreHuesped");
    //             String emailHuesped = resultSet.getString("emailHuesped");
    //             int idTarjeta = resultSet.getInt("idTarjeta");

    //             System.out.println("Información del Huesped:");
    //             System.out.println("ID Huesped: " + idHuesped);
    //             System.out.println("Nombre: " + nombreHuesped);
    //             System.out.println("Email: " + emailHuesped);
    //             System.out.println("ID Tarjeta: " + idTarjeta);
    //         } else {
    //             System.out.println("No se encontró ningún Huesped con la clave: " + idHuesped);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         System.err.println("Error al buscar el Huesped: " + e.getMessage());
    //     }
    // }

    public List<Huesped> obtenerHuespedes() throws SQLException {
        con = getCon();
        List<Huesped> huespedes = new ArrayList<>();
        String sqlConsulta = "SELECT * FROM huesped";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idHuesped = resultSet.getInt("idHuesped");
                String nombreHuesped = resultSet.getString("nombreHuesped");
                String emailHuesped = resultSet.getString("emailHuesped");
                int idTarjeta = resultSet.getInt("idTarjeta");

                Huesped huesped = new Huesped(idHuesped, nombreHuesped, emailHuesped, idTarjeta);
                huespedes.add(huesped);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al obtener los Huespedes: " + e.getMessage());
        }
        return huespedes;
    }   

    public Huesped buscarHuesped(String nombreHuesped) throws SQLException {
        con = getCon();
        Huesped huesped = null;
        String sqlConsulta = "SELECT * FROM huesped WHERE nombreHuesped = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setString(1, nombreHuesped);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idHuesped = resultSet.getInt("idHuesped");
                String nombre = resultSet.getString("nombreHuesped");
                String emailHuesped = resultSet.getString("emailHuesped");
                int idTarjeta = resultSet.getInt("idTarjeta");
                huesped = new Huesped(idHuesped, nombre, emailHuesped, idTarjeta);
            } else {
                System.out.println("No se encontró ningún Huesped con el nombre: " + nombreHuesped);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
        return huesped;
    }
}

