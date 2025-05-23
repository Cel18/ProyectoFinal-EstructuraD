package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedSocial implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private Map<String, Estudiante> estudiantes;
    private Map<String, Moderador> moderadores;
    private Map<String, Contenido> contenidos;
    private NodoContenido raiz;
    private GrafoAfinidad grafo;
    private ColaPrioridadSolicitudes colaSolicitudes;
    private List<GrupoEstudio> grupoEstudios;

    public RedSocial(String nombre) {
        this.nombre = nombre;
        this.estudiantes = Persistencia.cargarEstudiante();
        this.moderadores = Persistencia.cargarModeradores();
        this.contenidos = new HashMap<>();
        this.grafo = new GrafoAfinidad();
        this.colaSolicitudes = new ColaPrioridadSolicitudes();
        this.grupoEstudios = new ArrayList<>();
    }

    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.put(estudiante.getId(), estudiante);
        Persistencia.guardarEstudiantes(estudiantes);
    }

    public void registrarModerador(Moderador mod) {
        moderadores.put(mod.getId(), mod);
        Persistencia.guardarModeradores(moderadores);
    }

    public Estudiante autenticarEstudiante(String nombre, String contrasena) {
        for (Estudiante e : estudiantes.values()) {
            if (e.getNombre().equals(nombre) && e.getContrasena().equals(contrasena)) {
                return e;
            }
        }
        return null;
    }

    public Moderador autenticarModerador(String nombre, String contrasena) {
        for (Moderador m : moderadores.values()) {
            if (m.getNombre().equals(nombre) && m.getContrasena().equals(contrasena)) {
                return m;
            }
        }
        return null;
    }

    public void publicarContenido(Estudiante autor, Contenido contenido) {}
    public List<Contenido> buscarContenidoPorTema(String tema) {
        return List.of();
    }
    public List<Contenido> buscarContenidoPorAutor(String autor) {
        return List.of();
    }
    public List<Contenido> buscarContenidoPorTipo(String tipo) {
        return List.of();
    }
    public GrupoEstudio generarGrupoEstudio(Estudiante base){
        return null;
    }
    public void cargarDatosPrueba(){}
    public List<Contenido>  obtenerTodosContenidos() {
        return List.of();
    }
    public List<Estudiante> obtenerEstudiantes() {
        return List.of();
    }
    public GrafoAfinidad obtenerGrafo() {
        return null;
    }

    //getters and setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Map<String, Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Map<String, Moderador> getModeradores() {
        return moderadores;
    }

    public void setModeradores(Map<String, Moderador> moderadoresd) {
        this.moderadores = moderadores;
    }

    public Map<String, Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(Map<String, Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public NodoContenido getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoContenido raiz) {
        this.raiz = raiz;
    }

    public GrafoAfinidad getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoAfinidad grafo) {
        this.grafo = grafo;
    }

    public ColaPrioridadSolicitudes getColaSolicitudes() {
        return colaSolicitudes;
    }

    public void setColaSolicitudes(ColaPrioridadSolicitudes colaSolicitudes) {
        this.colaSolicitudes = colaSolicitudes;
    }

    public List<GrupoEstudio> getGrupoEstudios() {
        return grupoEstudios;
    }

    public void setGrupoEstudios(List<GrupoEstudio> grupoEstudios) {
        this.grupoEstudios = grupoEstudios;
    }
}
