package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;

import java.io.Serial;
import java.io.Serializable;

public class Contenido implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String tema;
    private String autor;
    private TipoContenido tipo;
    private String id;
    private ListaEnlazada<Valoracion> valoraciones;

    //Constructor de la clase Contenido
    public Contenido(String tema, String autor, TipoContenido tipo, String id) {
        this.tema = tema;
        this.autor = autor;
        this.tipo = tipo;
        this.id = id;
        this.valoraciones = Persistencia.cargarValoraciones();
    }

    //Getters y Setters
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public TipoContenido getTipo() {
        return tipo;
    }

    public void setTipo(TipoContenido tipo) {
        this.tipo = tipo;
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
}
