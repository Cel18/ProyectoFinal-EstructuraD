package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;
import proyectofinal.Utilidades.Utilidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class RedSocial implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private Map<String, Estudiante> estudiantes;
    private Map<String, Moderador> moderadores;
    private ListaEnlazada<Contenido> contenidos;
    private NodoContenido<Contenido> raiz;
    private GrafoAfinidad grafo;
    private ColaPrioridadSolicitudes colaSolicitudes;
    private List<GrupoEstudio> grupoEstudios;
    private Estudiante estudianteActivo; //estudiante loggeado
    private Moderador moderadorActivo;

    public RedSocial(String nombre) {
        this.nombre = nombre;
        this.estudiantes = Persistencia.cargarEstudianteMapa();
        this.moderadores = Persistencia.cargarModeradores();
        this.contenidos = Persistencia.cargarContenido();
        this.grafo = new GrafoAfinidad();
        this.colaSolicitudes = new ColaPrioridadSolicitudes();
        this.grupoEstudios = new ArrayList<>();
    }

    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.put(estudiante.getId(), estudiante);
        Persistencia.guardarEstudiantesMapa(estudiantes);
    }

    public void registrarModerador(Moderador mod) {
        moderadores.put(mod.getId(), mod);
        Persistencia.guardarModeradores(moderadores);
    }

    public Estudiante autenticarEstudiante(String nombre, String contrasena) {
        for (Estudiante e : estudiantes.values()) {
            if (e.getNombre().equals(nombre) && e.getContrasena().equals(contrasena)) {
                estudianteActivo = e;
                return e;
            }
        }
        return null;
    }

    public Moderador autenticarModerador(String nombre, String contrasena) {
        for (Moderador m : moderadores.values()) {
            if (m.getNombre().equals(nombre) && m.getContrasena().equals(contrasena)) {
                moderadorActivo = m;
                return m;
            }
        }
        return null;
    }

    public void publicarContenido(Estudiante autor, Contenido contenido) {
        if (!contenidos.buscarNodo(contenido)) {
            contenidos.insertarNodoInicio(contenido);
            autor.publicarContenido(contenido);
            Persistencia.guardarContenidos(contenidos);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en RedSocial. Correcto.");
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en RedSocial. Incorrecto.");
    }

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
    public void cargarDatosPrueba(){
        RedSocial redSocial = new RedSocial("RedSocialAprendizajePrueba");

        Estudiante est1 = new Estudiante("Sofia", "Buitrago", "333");
        Estudiante est2 = new Estudiante("Luis", "Martinez", "123");
        Estudiante est3 = new Estudiante("Valeria", "Torres", "456");
        Estudiante est4 = new Estudiante("Martinez", "Luis", "676");
        Estudiante est5 = new Estudiante("Lucia", "Martinez", "789");

        Moderador mod = new Moderador("Celeste", "111");

        redSocial.registrarEstudiante(est1);
        redSocial.registrarEstudiante(est2);
        redSocial.registrarEstudiante(est3);
        redSocial.registrarEstudiante(est4);
        redSocial.registrarEstudiante(est5);
        redSocial.registrarModerador(mod);

        // Crear el grafo con conexiones manuales (datos quemados)
        GrafoAfinidad grafo = new GrafoAfinidad();
        grafo.agregarEstudiante(est1);
        grafo.agregarEstudiante(est2);
        grafo.agregarEstudiante(est3);
        grafo.agregarEstudiante(est4);
        grafo.agregarEstudiante(est5);
        grafo.conectar(est1, est2);
        grafo.conectar(est2, est3);
        grafo.conectar(est3, est4);
        grafo.conectar(est4, est5);
        grafo.conectar(est5, est1);
        grafo.conectar(est1, est4);

        redSocial.setGrafo(grafo);

        Persistencia.guardarRedSocial(redSocial);

        System.out.println("== Estudiantes cargados ==");
        redSocial.getEstudiantes().forEach((id, estudiante) -> {
            System.out.println("Nombre: " + estudiante.getNombre() + ", Contraseña: " + estudiante.getContrasena());
        });
        System.out.println("== Moderadores cargados ==");
        redSocial.getModeradores().forEach((id, moderador) -> {
            System.out.println("Nombre: " + moderador.getNombre() + ", Contraseña: " + moderador.getContrasena());
        });
    }
    public List<Contenido>  obtenerTodosContenidos() {
        return List.of();
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

    public ListaEnlazada<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(ListaEnlazada<Contenido> contenidos) {
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

    public Estudiante getEstudianteActivo() {
        return estudianteActivo;
    }

    public void setEstudianteActivo(Estudiante estudianteActivo) {
        this.estudianteActivo = estudianteActivo;
    }

    public Moderador getModeradorActivo() {
        return moderadorActivo;
    }

    public void setModeradorActivo(Moderador moderadorActivo) {
        this.moderadorActivo = moderadorActivo;
    }
}
