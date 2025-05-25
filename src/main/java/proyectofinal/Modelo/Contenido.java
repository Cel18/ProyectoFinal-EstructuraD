package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class Contenido implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String tema;
    private Usuario autor;
    private TipoContenido tipo;
    private String id;
    private ListaEnlazada<Valoracion> valoraciones;

    //Constructor de la clase Contenido
    public Contenido(String tema, Usuario autor, TipoContenido tipo) {
        this.tema = tema;
        this.autor = autor;
        this.tipo = tipo;
        this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.valoraciones = Persistencia.cargarValoraciones();
    }

    //Getters y Setters
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
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
