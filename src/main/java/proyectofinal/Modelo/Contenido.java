package proyectofinal.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Contenido {
    private String tema;
    private String autor;
    private String tipo;
    private String id;
    private List<Valoracion> valoraciones;

    public Contenido(String tema, String autor, String tipo, String id) {
        this.tema = tema;
        this.autor = autor;
        this.tipo = tipo;
        this.id = id;
        this.valoraciones = new ArrayList();
    }
}
