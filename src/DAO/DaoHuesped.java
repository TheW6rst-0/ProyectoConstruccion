package DAO;
import java.sql.*;

import Model.Huesped;
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

    public void modificarHuesped() throws SQLException {
        con = getCon();

    }

    public void buscarHuesped(int idHuesped) throws SQLException {
        con = getCon();
        String sqlConsulta = "SELECT * FROM huesped WHERE idHuesped = ?";
        try (PreparedStatement statement = con.prepareStatement(sqlConsulta)) {
            statement.setInt(1, idHuesped);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                int id = resultSet.getInt("idHuesped");
                String nombreHuesped = resultSet.getString("nombreHuesped");
                String emailHuesped = resultSet.getString("emailHuesped");
                int idTarjeta = resultSet.getInt("idTarjeta");

                System.out.println("Información del empleado:");
                System.out.println("ID Huesped: " + idHuesped);
                System.out.println("Nombre: " + nombreHuesped);
                System.out.println("Email: " + emailHuesped);
                System.out.println("ID Tarjeta: " + idTarjeta);
            } else {
                System.out.println("No se encontró ningún empleado con la clave: " + idHuesped);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar el empleado: " + e.getMessage());
        }
    }
}

