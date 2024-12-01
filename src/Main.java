import DAO.DaoEmpleado;
import Model.Administrador;
import DAO.DaoHuesped;
import Model.Huesped;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        DaoEmpleado dao = new DaoEmpleado();
        Administrador admin = new Administrador(23,"asdfssdf","efsafdfs","mjaja");
        dao.buscarEmpleado(23);
        DaoHuesped dao2 = new DaoHuesped();
        Huesped huesped = new Huesped(2, "Skibidi", "eresputo@gmail.com", 235);
        dao2.agregarHuesped(huesped);

    }
}