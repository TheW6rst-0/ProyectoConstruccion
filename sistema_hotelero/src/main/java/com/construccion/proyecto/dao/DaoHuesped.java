package com.construccion.proyecto.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.construccion.proyecto.model.Huesped;
/**
 * Clase encargada de realizar las operaciones CRUD sobre la tabla "huesped" en la base de datos.
 */

public class DaoHuesped {
    private Connection con = null;
    private final String host = "jdbc:mysql://localhost:3306/hotel";
    private final String user = "root";
    private final String pass = "";


    /**
     * Establece la conexión con la base de datos.
     * 
     * @return la conexión a la base de datos.
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
     * Agrega un nuevo huésped a la base de datos.
     * 
     * @param huesped objeto de tipo Huesped que contiene los datos a insertar.
     * @return true si la operación fue exitosa, false en caso de error.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */

    public boolean agregarHuesped(Huesped huesped) throws SQLException {
        con = getCon();
        String sql = "INSERT INTO huesped (idHuesped,nombreHuesped,emailHuesped,idTarjeta) VALUES(?,?,?,?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, huesped.getIdHuesped());
            statement.setString(2, huesped.getNombre());
            statement.setString(3, huesped.getEmail());
            statement.setInt(4, huesped.getIdTarjeta());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    
    
    /**
     * Elimina un huésped de la base de datos.
     * 
     * @param huesped objeto de tipo Huesped que contiene el ID del huésped a eliminar.
     * @return true si la operación fue exitosa, false en caso de error.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */

    public boolean eliminarHuesped(Huesped huesped) throws SQLException {
        con = getCon();
        String sql = "DELETE FROM huesped WHERE idHuesped=?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, huesped.getIdHuesped());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        /**
     * Modifica los datos de un huésped en la base de datos.
     * 
     * @param huesped objeto de tipo Huesped que contiene los datos a modificar.
     * @return true si la operación fue exitosa, false en caso de que no se encuentre el huésped.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */

    public boolean modificarHuesped(Huesped huesped) throws SQLException {
        con = getCon();
        String sql = "UPDATE huesped SET nombreHuesped = ?, emailHuesped = ?, idTarjeta = ? WHERE idHuesped = ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, huesped.getNombre());
            statement.setString(2, huesped.getEmail());
            statement.setInt(3, huesped.getIdTarjeta());
            statement.setInt(4, huesped.getIdHuesped());
    
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Huésped actualizado exitosamente.");
                return true;
            } else {
                System.out.println("No se encontró ningún huésped con el ID especificado.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Busca un huésped por su ID.
     * 
     * @param idHuesped ID del huésped a buscar.
     * @return el objeto Huesped encontrado o null si no se encuentra.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */

    public Huesped buscarHuesped(int idHuesped) throws SQLException {
        con = getCon();
        Huesped huesped = null;
        String sqlConsulta = "SELECT * FROM huesped WHERE idHuesped = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, idHuesped);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idHuesped");
                String nombreHuesped = resultSet.getString("nombreHuesped");
                String emailHuesped = resultSet.getString("emailHuesped");
                int idTarjeta = resultSet.getInt("idTarjeta");
                huesped = new Huesped(id, nombreHuesped, emailHuesped, idTarjeta);
            } else {
                System.out.println("No se encontró ningún Huesped con la clave: " + idHuesped);
            }
        } catch (SQLException e) {
           
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
        return huesped;
    }
    /**
     * Obtiene todos los huéspedes de la base de datos.
     * 
     * @return una lista de objetos Huesped con todos los huéspedes.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */

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
            
            System.err.println("Error al obtener los Huespedes: " + e.getMessage());
        }
        return huespedes;
    }   
    /**
     * Busca un huésped por su nombre.
     * 
     * @param nombreHuesped nombre del huésped a buscar.
     * @return el objeto Huesped encontrado o null si no se encuentra.
     * @throws SQLException si ocurre un error al ejecutar la consulta.
     */

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
           
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
        return huesped;
    }
}

