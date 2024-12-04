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
            System.out.println("Conexion exitosa");
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
            e.printStackTrace();
            System.err.println("Error al modificar la habitación: " + e.getMessage());
        }
    }
    

    public void buscarHabitacion(int idHabitacion) throws SQLException {
        con = getCon();
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

                System.out.println("Información de la Habitacion:");
                System.out.println("ID Huesped: " + id);
                System.out.println("Nombre: " + tipoHabitacion);
                System.out.println("Camas: " + camas);
                System.out.println("ID Tarjeta: " + precio);
                System.out.println("Disponibilidad: " + disponibilidad);
            } else {
                System.out.println("No se encontró ningún Huesped con la clave: " + idHabitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al buscar el Huesped: " + e.getMessage());
        }
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
    

    public List<Integer> getHabitacionesPorTipo(String tipo) throws SQLException {
    List<Integer> ids = new ArrayList<>();
    String sql = "SELECT idHabitacion FROM habitacion WHERE tipoHabitacion = ?";
    con = getCon();
    try (PreparedStatement statement = con.prepareStatement(sql)) {
        statement.setString(1, tipo);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ids.add(resultSet.getInt("idHabitacion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ids;
}

public boolean getDisponibilidadPorId(int idHabitacion) throws SQLException {
    String sql = "SELECT disponibilidad FROM habitacion WHERE idHabitacion = ?";
    con = getCon();
    try (PreparedStatement statement = con.prepareStatement(sql)) {
        statement.setInt(1, idHabitacion);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getBoolean("disponibilidad");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    throw new SQLException("No se encontró la habitación con ID: " + idHabitacion);
}

public void actualizarDisponibilidad(int idHabitacion, boolean nuevaDisponibilidad) throws SQLException {
    String sql = "UPDATE habitacion SET disponibilidad = ? WHERE idHabitacion = ?";
    con = getCon();
    try (PreparedStatement statement = con.prepareStatement(sql)) {
        statement.setBoolean(1, nuevaDisponibilidad);
        statement.setInt(2, idHabitacion);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Disponibilidad actualizada para ID: " + idHabitacion);
        } else {
            System.out.println("No se encontró ninguna habitación con el ID especificado.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        throw new SQLException("Error al actualizar la disponibilidad: " + e.getMessage());
    }
}

public List<Integer> getAllHabitaciones() throws SQLException {
    List<Integer> ids = new ArrayList<>();
    String sql = "SELECT idHabitacion FROM habitacion";
    con = getCon();
    try (PreparedStatement statement = con.prepareStatement(sql)) {
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            ids.add(resultSet.getInt("idHabitacion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ids;
}


}
