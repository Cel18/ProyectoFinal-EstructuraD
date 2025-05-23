package proyectofinal.Modelo;

import java.io.Serializable;

public class Moderador extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    public Moderador(String nombre, String contrasena) {
        super(nombre, contrasena);
    }

    public void generarReporteContenidos(){}

    public void analizarGrafoAfinidad(GrafoAfinidad grafo){}
}
