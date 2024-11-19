package Model;

public class Huesped {
    private String nombre;
    private String id;
    private String email;
    private Tarjeta tarjeta;
    public Huesped(String nombre, String id, String email, Tarjeta tarjeta) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
        this.tarjeta = tarjeta;
    }
}
