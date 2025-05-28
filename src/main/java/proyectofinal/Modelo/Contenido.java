package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;
import proyectofinal.Utilidades.Utilidades;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;

public class Contenido implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String tema;
    private Estudiante autor;
    private TipoContenido tipo;
    private final LocalDate fechaPublicacion;
    private String id;
    private ListaEnlazada<Valoracion> valoraciones;

    //Constructor de la clase Contenido
    public Contenido(String tema, Estudiante autor, TipoContenido tipo) {
        this.tema = tema;
        this.autor = autor;
        this.tipo = tipo;
        this.fechaPublicacion = LocalDate.now();
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.valoraciones = new ListaEnlazada<>();
    }

    //Método para buscar una valoración en el contenido

    public NodoContenido<Valoracion> buscarValoracionPorNombre(String nombre) {
        NodoContenido<Valoracion> nodoRecorrer = valoraciones.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getEstudiante().getNombre().equals(nombre)) {
                Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarValoracionPorNombre en Contenido. Correcto.");
                return nodoRecorrer;
            }

            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO,"Método buscarValoracionPorNombre en Contenido. No se encontró el nodo.");
        return null;
    }

    //Getters y Setters
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Estudiante getAutor() {
        return autor;
    }

    public void setAutor(Estudiante autor) {
        this.autor = autor;
    }

    public TipoContenido getTipo() {
        return tipo;
    }

    public void setTipo(TipoContenido tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ListaEnlazada<Valoracion> getValoraciones() {
        return valoraciones;
    }

    @Override
    public String toString() {
        return tema + ", " + autor + ", " + tipo + ", " + id + ".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contenido other = (Contenido) obj;
        return this.tema.equals(other.tema)
                && this.autor.getNombreCompleto().equals(other.autor.getNombreCompleto())
                && this.tipo == other.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tema, autor.getNombreCompleto(), tipo);
    }

}
