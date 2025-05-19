package proyectofinal.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private String apellido;
    private String id;
    private List<Contenido> contenidosPublicados;
    private List<Valoracion> valoraciones;
    private List<Estudiante> conexiones;

    public Estudiante(String nombre, String apellido, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.contenidosPublicados = new ArrayList<>();
        this.valoraciones = new ArrayList<>();
        this.conexiones = new ArrayList<>();
    }

    //getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Contenido> getContenidosPublicados() {
        return contenidosPublicados;
    }

    public void setContenidosPublicados(List<Contenido> contenidosPublicados) {
        this.contenidosPublicados = contenidosPublicados;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public List<Estudiante> getConexiones() {
        return conexiones;
    }

    public void setConexiones(List<Estudiante> conexiones) {
        this.conexiones = conexiones;
    }
}
