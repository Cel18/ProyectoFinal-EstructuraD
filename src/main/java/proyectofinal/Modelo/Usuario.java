package proyectofinal.Modelo;

import java.io.Serializable;
import java.util.UUID;

public abstract class Usuario implements Serializable {
    protected String id;
    protected String nombre;
    protected String contrasena;

    public Usuario(String nombre, String contrasena) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public boolean autenticarConstrasena(String id){
        return this.contrasena.equals(id);
    }

    //getters and setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String id) {
        this.contrasena = id;
    }
}
