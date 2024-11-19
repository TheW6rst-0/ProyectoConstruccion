import DAO.DaoEmpleado;
import Model.Administrador;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello world!");
        DaoEmpleado dao = new DaoEmpleado();
        Administrador admin = new Administrador(23,"asdfssdf","efsafdfs","mjaja");
        dao.buscarEmpleado(23);

    }
}