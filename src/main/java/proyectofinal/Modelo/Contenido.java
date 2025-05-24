package proyectofinal.Modelo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Contenido implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String tema;
    private String autor;
    private TipoContenido tipo;
    private String id;
    private List<Valoracion> valoraciones;

    //Constructor de la clase Contenido
    public Contenido(String tema, String autor, TipoContenido tipo, String id) {
        this.tema = tema;
        this.autor = autor;
        this.tipo = tipo;
        this.id = id;
        this.valoraciones = new ArrayList();
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

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }

    @Override
    public String toString() {
        return tema + ", " + autor + ", " + tipo + ", " + id + ".";
    }
}
