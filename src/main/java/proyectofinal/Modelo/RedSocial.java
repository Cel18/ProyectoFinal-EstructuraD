package proyectofinal.Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedSocial {
    private String nombre;
    private Map<String, Estudiante> estudiantesPorId;
    private Map<String, Moderador> moderadoresPorId;
    private Map<String, Contenido> contenidosPorId;
    private NodoContenido raiz;
    private GrafoAfinidad grafo;
    private ColaPrioridadSolicitudes colaSolicitudes;
    private List<GrupoEstudio> grupoEstudios;

    public RedSocial(String nombre) {
        this.nombre = nombre;
        estudiantesPorId = new HashMap<>();
        moderadoresPorId = new HashMap<>();
        contenidosPorId = new HashMap<>();
        grafo = new GrafoAfinidad();
        colaSolicitudes = new ColaPrioridadSolicitudes();
        grupoEstudios = new ArrayList<>();
    }
}
