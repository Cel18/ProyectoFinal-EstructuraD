package proyectofinal.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Contenido {
    private String tema;
    private String autor;
    private String tipo;
    private String id;
    private List<Valoracion> valoraciones;

    //Constructor de la clase Contenido
    public Contenido(String tema, String autor, String tipo, String id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
}
