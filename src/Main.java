import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import DAO.*;
import Model.*;
public class Main {
    public static void main(String[] args) throws SQLException {
    DaoReservas dao = new DaoReservas();
    LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2024,12,02);
    Reservacion reservacion = new Reservacion(21,2,64,today,tomorrow);
    dao.agregarReservacion(reservacion);

    }
}