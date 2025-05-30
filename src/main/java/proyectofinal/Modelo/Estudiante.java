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
        this.contenidosPublicados = new ListaEnlazada<>(); //Persistencia.cargarContenido(getNombreCompleto());
        this.valoraciones = new ListaEnlazada<>(); //Persistencia.cargarValoraciones(getNombreCompleto());
        this.conexiones = new ListaEnlazada<>(); //Persistencia.cargarConexiones(getNombreCompleto());
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

    public NodoContenido<Contenido> buscarContenidoAutor(Estudiante autor) {
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
            Persistencia.guardarContenidos(contenidosPublicados, getNombreCompleto());
            Utilidades.getInstance().escribirLog(Level.INFO,"Método publicarContenido en Estudiante. Correcto.");
            return;
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en Estudiante. Incorrecto, el contenido ya está publicado.");
    }

    //Método para eliminar un contenido subido por el estudiante

    public void eliminarContenido(Contenido contenido) {
        NodoContenido<Contenido> nodoRecorrer = contenidosPublicados.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().equals(contenido)) {
                contenidosPublicados.eliminarNodo(nodoRecorrer.getContenido());
                Persistencia.guardarContenidos(contenidosPublicados, getNombreCompleto());
                Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarContenido en Estudiante. Correcto.");
                return;
            }

            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarContenido en Estudiante. No se eliminó el contenido.");
    }

    //Método para valorar contenido

    public void valorarContenido(Contenido contenido, int puntuacion, String comentario) {
        if (puntuacion < 1 || puntuacion > 5) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método valorarContenido en Estudiante. Puntaje fuera de rango.");
            return;
        }

        Valoracion nuevaValoracion = new Valoracion(this, contenido, puntuacion, comentario);

        if (!valoraciones.buscarNodo(nuevaValoracion)) {
            valoraciones.insertarNodoInicio(nuevaValoracion);
            Persistencia.guardarValoraciones(valoraciones, getNombreCompleto());

            // Agregar la valoración también al contenido
            if (!contenido.getValoraciones().buscarNodo(nuevaValoracion)) {
                contenido.getValoraciones().insertarNodoInicio(nuevaValoracion);
                Persistencia.guardarValoraciones(contenido.getValoraciones(), getNombreCompleto());
            }

            // Crear conexión con otros estudiantes que ya hayan valorado este contenido
            NodoContenido<Valoracion> actual = contenido.getValoraciones().getInicial();
            while (actual != null) {
                Valoracion valoracionExistente = actual.getContenido();
                Estudiante otroEstudiante = valoracionExistente.getEstudiante();

                if (!otroEstudiante.equals(this)) {
                    this.agregarConexion(otroEstudiante);
                    otroEstudiante.agregarConexion(this);
                }

                actual = actual.getDerecho();
            }

            Utilidades.getInstance().escribirLog(Level.INFO, "Método valorarContenido en Estudiante. Correcto.");
        } else {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método valorarContenido en Estudiante. Ya se ha valorado este contenido.");
        }
    }

    //Método para eliminar una valoracion

    public void eliminarValoracion(Valoracion valoracion) {
        NodoContenido<Valoracion> nodoRecorrer = valoraciones.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().equals(valoracion)) {
                valoraciones.eliminarNodo(nodoRecorrer.getContenido());
                Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarValoracion en Estudiante. Correcto.");
                Persistencia.guardarValoraciones(valoraciones, getNombreCompleto());
                return;
            }

            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarValoracion en Estudiante. No se eliminó la valoración");
    }

    //Metodo para obtener el promedio de las valoraciones que ha hecho el estudiante

    public double getPromedioValoraciones() {
        double suma = 0;
        int cantidad = 0;

        NodoContenido<Valoracion> actual = valoraciones.getInicial();

        while (actual != null) {
            suma += actual.getContenido().getPuntuacion();
            cantidad++;
            actual = actual.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método getPromedioValoraciones en Estudiante. Correcto.");
        return cantidad > 0 ? suma / cantidad : 0;
    }

    // valoraciones de los contenidos del estudiante

    public double getPromedioValoracionesRecibidas() {
        double suma = 0;
        int cantidad = 0;

        NodoContenido<Contenido> nodoContenido = contenidosPublicados.getInicial();

        while (nodoContenido != null) {
            Contenido contenido = nodoContenido.getContenido();
            NodoContenido<Valoracion> valoracionNodo = contenido.getValoraciones().getInicial();

            while (valoracionNodo != null) {
                suma += valoracionNodo.getContenido().getPuntuacion();
                cantidad++;
                valoracionNodo = valoracionNodo.getDerecho();
            }

            nodoContenido = nodoContenido.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método getPromedioValoracionesRecibidas en Estudiante. Correcto.");
        return cantidad > 0 ? suma / cantidad : 0;
    }


    //Método para agregar conexión con otro estudiante

    public void agregarConexion(Estudiante estudiante) {
        if (!conexiones.buscarNodo(estudiante)) {
            conexiones.insertarNodoInicio(estudiante);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método agregarConexion en Estudiante. Correcto.");
            Persistencia.guardarEstudiante(this, getNombreCompleto());
            return;
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método agregarConexion en Estudiante. Ya existe la conexión.");
    }

    //Método para eliminar conexión con otro estudiante

    public void eliminarConexion(Estudiante estudiante) {
        NodoContenido<Estudiante> nodoRecorrer = conexiones.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().toString().equals(estudiante.toString())) {
                conexiones.eliminarNodo(nodoRecorrer.getContenido());
                Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarConexion en Estudiante. Correcto.");
                Persistencia.guardarConexiones(conexiones, getNombreCompleto());
                return;
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método agregarConexion en Estudiante. No se eliminó la conexión.");
    }

    //Método para buscar conexión con otro estudiante por nombre (solo por el nombre)

    public Estudiante buscarConexionPorNombre(String nombre) {
        NodoContenido<Estudiante> nodoRecorrer = conexiones.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getNombre().equals(nombre)) {
                Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarValoracionPorNombre en Contenido. Correcto.");
                return nodoRecorrer.getContenido();
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarValoracionPorNombre en Contenido. No se encontró.");
        return null;
    }

    //Método para la sugerencia de la conexión

    public ListaEnlazada<Estudiante> obtenerSugerenciasDeConexion() {

        Utilidades.getInstance().escribirLog(Level.INFO,"Método obtenerSugerenciasDeConexion en Estudiante. Correcto.");
        return null;
    }

    //Método para enviar mensaje a otro estudiante

    /*public void enviarMensaje(Estudiante destinatario, String mensajeTexto) {
        Mensaje mensaje = new Mensaje(this, destinatario, mensajeTexto, LocalDateTime.now());
        redSocial.agregarMensaje(mensaje);
        Utilidades.getInstance().escribirLog(Level.INFO, "Mensaje enviado correctamente.");
    }*/

    //Método para generar el nombre completo del estudiante

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

    public void setContenidosPublicados(ListaEnlazada<Contenido> contenidosPublicados) {
        this.contenidosPublicados = contenidosPublicados;
    }

    public ListaEnlazada<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public ListaEnlazada<Estudiante> getConexiones() {
        return conexiones;
    }

    public void setConexiones(ListaEnlazada<Estudiante> conexiones) {
        this.conexiones = conexiones;
    }

    //Override

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
                this.apellido.equals(that.apellido);
    }

    @Override
    public int hashCode() {
        return (getNombre() + getApellido()).hashCode();
    }
}
