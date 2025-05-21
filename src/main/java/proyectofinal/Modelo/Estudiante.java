package proyectofinal.Modelo;

import proyectofinal.Utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Estudiante extends Usuario {
    private String apellido;
    private static List<Contenido> contenidosPublicados;
    private static List<Valoracion> valoraciones;
    private static List<Estudiante> conexiones;

    //Constructor de la clase Estudiante
    public Estudiante(String nombre, String apellido, String id) {
        super(nombre, id);
        this.apellido = apellido;
        this.contenidosPublicados = new ArrayList<>();
        this.valoraciones = new ArrayList<>();
        this.conexiones = new ArrayList<>();
    }
    
    //Métodos para buscar contenido
    
    public void buscarContenidoTema(String tema) {
        //Falta
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoTema en Estudiante. Correcto.");
    }

    public void buscarContenidoAutor(String autor) {
        //Falta
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoAutor en Estudiante. Correcto.");
    }

    public void buscarContenidoTipo(String tipo) {
        //Falta
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoTipo en Estudiante. Correcto.");
    }

    //Método para publicar contenido

    public void publicarContenido(Contenido contenido) {
        if (!contenidosPublicados.contains(contenido)) {
            contenidosPublicados.add(contenido);
            Utilidades.getInstance().escribirLog(Level.INFO,"Método publicarContenido en Estudiante. Correcto.");
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en Estudiante. Incorrecto, el contenido ya está publicado.");
    }

    //Método para obtener el contenido publicado del estudiante
    public List<Contenido> obtenerContenidoPublicado(){
        return List.of();
    }

    //Método para valorar contenido

    public void valorarContenido(Contenido contenido, int puntaje) {
        //Falta
        Utilidades.getInstance().escribirLog(Level.INFO, "Método valorarContenido en Estudiante. Correcto.");
    }

    //Método para solicitar ayuda

    public void solicitarAyuda(SolicitudAyuda solicitudAyuda) {
        //Falta
        Utilidades.getInstance().escribirLog(Level.INFO, "Método solicitarAyuda en Estudiante. Correcto.");
    }

    //Método para agregar conexión con otro estudiante

    public void agregarConexion(Estudiante estudiante) {

    }

    //Método para la sugerencia de la conexión

    public List<Estudiante> obtenerSugerenciasDeConexion(){
        return List.of();
    }

    //Método para unirse a un grupo de estudio

    public void unirseAGrupoEstudio(List<Estudiante> grupo) {

    }

    //Método para enviar mensaje a otro estudiante
    public void enviarMensaje(Estudiante destinatario, String mensaje) {

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
