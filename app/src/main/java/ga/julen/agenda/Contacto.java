package ga.julen.agenda;

public class Contacto {

    private int id;
    private String nombre;
    private String apellido;
    private String numero;
    private String mail;

    public Contacto(int id, String nombre, String apellido, String numero, String mail) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNumero() {
        return numero;
    }

    public String getMail() {
        return mail;
    }
}
