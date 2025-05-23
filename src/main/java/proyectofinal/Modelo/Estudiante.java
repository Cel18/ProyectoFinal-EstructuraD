package proyectofinal.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private String apellido;
    private List<Contenido> contenidosPublicados;
    private List<Valoracion> valoraciones;
    private List<Estudiante> conexiones;

    //Constructor de la clase Estudiante
    public Estudiante(String nombre, String apellido, String contrasena) {
        super(nombre, contrasena);
        this.apellido = apellido;
        this.contenidosPublicados = new ArrayList<>();
        this.valoraciones = new ArrayList<>();
        this.conexiones = new ArrayList<>();
    }

    public void publicarContenido(Contenido contenido) {}
    public void valorarContenido(Contenido contenido, int puntaje) {}
    public void solicitarAyuda(SolicitudAyuda solicitudAyuda) {}
    public void agregarConexion(Estudiante estudiante) {}
    public List<Estudiante> obtenerSugerenciasDeConexion(){
        return List.of();
    }
    public void unirseAGrupoEstudio(List<Estudiante> grupo) {}
    public void enviarMensaje(Estudiante destinatario, String mensaje) {}
    public List<Contenido> obtenerContenidoPublicado(){
        return List.of();
    }

    //Getters and Setters
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
