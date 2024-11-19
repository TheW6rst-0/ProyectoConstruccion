package Model;

public class Administrador extends Empleado {
    public Administrador(int clave,String nombre, String usuario, String contrasenia) {
        super(clave,nombre, usuario, contrasenia);
        this.claveEmp=clave;
        this.usuario=usuario;
        this.nombre=nombre;
        this.contrasenia=contrasenia;

    }

}
