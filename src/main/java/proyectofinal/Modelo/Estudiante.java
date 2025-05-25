package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;
import proyectofinal.Utilidades.Utilidades;

import java.io.Serial;
import java.io.Serializable;
import java.util.logging.Level;

public class Estudiante extends Usuario implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String apellido;
    private final RedSocial redSocial = Persistencia.cargarRedSocial();
    private ListaEnlazada<Contenido> contenidosPublicados;
    private ListaEnlazada<Valoracion> valoraciones;
    private ListaEnlazada<Estudiante> conexiones;

    //Constructor de la clase Estudiante
    public Estudiante(String nombre, String apellido, String contrasena) {
        super(nombre, contrasena);
        this.apellido = apellido;
        this.contenidosPublicados = Persistencia.cargarContenido();
        this.valoraciones = Persistencia.cargarValoraciones();
        this.conexiones = Persistencia.cargarEstudianteLista();
    }

    //Métodos para buscar contenido

    public NodoContenido<Contenido> buscarContenidoTema(String tema) {
        NodoContenido<Contenido> nodoRecorrer = contenidosPublicados.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getTema().equals(tema)) {
                Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoTema en Estudiante. Correcto.");
                return nodoRecorrer;
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoTema en Estudiante. Incorrecto, no se encontró el contenido publicado.");
        return null;
    }

    public NodoContenido<Contenido> buscarContenidoAutor(String autor) {
        NodoContenido<Contenido> nodoRecorrer = contenidosPublicados.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getAutor().equals(autor)) {
                Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoAutor en Estudiante. Correcto.");
                return nodoRecorrer;
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoAutor en Estudiante. Incorrecto, no se encontró el contenido publicado.");
        return null;
    }

    public NodoContenido<Contenido> buscarContenidoTipo(TipoContenido tipo) {
        NodoContenido<Contenido> nodoRecorrer = contenidosPublicados.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getTipo().equals(tipo)) {
                Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoTipo en Estudiante. Correcto.");
                return nodoRecorrer;
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoTipo en Estudiante. Incorrecto, no se encontró el contenido publicado.");
        return null;
    }

    //Método para publicar contenido

    public void publicarContenido(Contenido contenido) {
        if (!contenidosPublicados.buscarNodo(contenido)) {
            contenidosPublicados.insertarNodoInicio(contenido);
            Persistencia.guardarContenidos(contenidosPublicados);
            Utilidades.getInstance().escribirLog(Level.INFO,"Método publicarContenido en Estudiante. Correcto.");
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en Estudiante. Incorrecto, el contenido ya está publicado.");
    }

    //Método para valorar contenido

    public void valorarContenido(Contenido contenido, int puntaje) {
        //Falta
        Utilidades.getInstance().escribirLog(Level.INFO, "Método valorarContenido en Estudiante. Correcto.");
    }

    public void solicitarAyuda(SolicitudAyuda solicitudAyuda) {
        //Falta
        Utilidades.getInstance().escribirLog(Level.INFO, "Método solicitarAyuda en Estudiante. Correcto.");
    }

    //Método para agregar conexión con otro estudiante

    public void agregarConexion(Estudiante estudiante) {

        Utilidades.getInstance().escribirLog(Level.INFO, "Método agregarConexion en Estudiante. Correcto.");
    }

    //Método para la sugerencia de la conexión

    public ListaEnlazada<Estudiante> obtenerSugerenciasDeConexion() {

        Utilidades.getInstance().escribirLog(Level.INFO,"Método obtenerSugerenciasDeConexion en Estudiante. Correcto.");
        return null;
    }

    //Método para unirse a un grupo de estudio

    public void unirseAGrupoEstudio(ListaEnlazada<Estudiante> grupo) {

        Utilidades.getInstance().escribirLog(Level.INFO,"Método unirseAGrupoEstudio en Estudiante. Correcto.");
    }

    //Método para enviar mensaje a otro estudiante

    public void enviarMensaje(Estudiante destinatario, String mensaje) {

    }

    //Método para obtener los contenidos publicados por el estudiante

    public ListaEnlazada<Contenido> obtenerContenidoPublicado() {
        return null;
    }

    public double getPromedioValoraciones() {
        if (valoraciones == null || valoraciones.getTamanio() == 0) return 0;
        int suma = 0;
        int count = 0;

        NodoContenido<Valoracion> actual = valoraciones.getInicial();
        while (actual != null) {
            suma += actual.getContenido().getPuntuacion();
            count++;
            actual = actual.getDerecho();
        }

        return count > 0 ? (double) suma / count : 0;
    }

    public String getNombreCompleto() {
        return this.getNombre() + " " + this.apellido;
    }

    //Getters and Setters
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ListaEnlazada<Contenido> getContenidosPublicados() {
        return contenidosPublicados;
    }

    public ListaEnlazada<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public ListaEnlazada<Estudiante> getConexiones() {
        return conexiones;
    }

    @Override
    public String toString() {
        return getNombreCompleto();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estudiante that = (Estudiante) obj;
        return this.getNombre().equals(that.getNombre()) &&
                this.getApellido().equals(that.getApellido());
    }

    @Override
    public int hashCode() {
        return (getNombre() + getApellido()).hashCode();
    }
}
