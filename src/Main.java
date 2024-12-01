import DAO.DaoEmpleado;
import Model.Administrador;
import DAO.DaoHuesped;
import Model.Huesped;
import Model.Tarjeta;
import DAO.DaoTarjeta;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        DaoHuesped dao2 = new DaoHuesped();
        DaoTarjeta dao3 = new DaoTarjeta();
        Date date = new Date();
        Tarjeta tarjeta = new Tarjeta(235,"skibidi","983245359694","241",date,14000);
        dao3.agregarTarjeta(tarjeta);
        Huesped huesped = new Huesped(2, "Skibidi", "eresputo@gmail.com", 235);
        dao2.agregarHuesped(huesped);

    }
}