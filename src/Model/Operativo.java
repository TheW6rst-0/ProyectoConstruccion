package Model;

public class Operativo extends Empleado{
    public Operativo( int clave, String nombre, String usuario, String contrasenia) {
        super( clave,nombre, usuario, contrasenia);
        this.claveEmp=clave;
        this.usuario=usuario;
        this.nombre=nombre;
        this.contrasenia=contrasenia;
    }
}
