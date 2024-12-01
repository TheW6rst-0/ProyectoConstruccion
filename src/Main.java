import java.sql.SQLException;
import java.util.Date;
import DAO.*;
import Model.*;
public class Main {
    public static void main(String[] args) throws SQLException {
        DaoHabitaciones dao = new DaoHabitaciones();
        Habitacion habitacion = new Habitacion(64,"Pendejo","230",50000,true);
        dao.agregarHabitacioens(habitacion);


    }
}