package proyectofinal.Modelo;

import jdk.jshell.execution.Util;
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


    //Constructor

    public RedSocial(String nombre) {
        this.nombre = nombre;
        this.estudiantes = new HashMap<>();
        this.moderadores = new HashMap<>();
        this.contenidos = new ListaEnlazada<>();
        this.grafo = new GrafoAfinidad();
        this.colaSolicitudes = new ColaPrioridadSolicitudes();
        this.grupoEstudios = new ArrayList<>();
    }

    //Método para registrar un estudiante en Red Social

    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.put(estudiante.getId(), estudiante);
        Persistencia.guardarEstudiantesMapa(estudiantes);
        Utilidades.getInstance().escribirLog(Level.INFO, "Método registrarEstudiante en RedSocial. Correcto.");
    }

    //Método para registrar un moderador en Red Social

    public void registrarModerador(Moderador mod) {
        moderadores.put(mod.getId(), mod);
        Persistencia.guardarModeradores(moderadores);
        Utilidades.getInstance().escribirLog(Level.INFO, "Método registrarModerador en RedSocial. Correcto.");
    }

    //Método para autenticar un estudiante en Red Social

    public Estudiante autenticarEstudiante(String nombre, String contrasena) {
        for (Estudiante e : estudiantes.values()) {
            if (e.getNombre().equals(nombre) && e.getContrasena().equals(contrasena)) {
                estudianteActivo = e;
                Utilidades.getInstance().escribirLog(Level.INFO, "Método autenticarEstudiante en RedSocial. Correcto.");
                return e;
            }
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método autenticarEstudiante en RedSocial. No se autenticó el estudiante.");
        return null;
    }

    //Método para autenticar un moderador en Red Social

    public Moderador autenticarModerador(String nombre, String contrasena) {
        for (Moderador m : moderadores.values()) {
            if (m.getNombre().equals(nombre) && m.getContrasena().equals(contrasena)) {
                moderadorActivo = m;
                Utilidades.getInstance().escribirLog(Level.INFO, "Método autenticarModerador en RedSocial. Correcto.");
                return m;
            }
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método autenticarModerador en RedSocial. No se autenticó el moderador.");
        return null;
    }

    //Método para publicar el contenido de un estudiante en Red Social

    public void publicarContenido(Estudiante autor, Contenido contenido) {
        if (!contenidos.buscarNodo(contenido)) {
            contenidos.insertarNodoInicio(contenido);
            Persistencia.guardarContenidos(contenidos,"RedSocial");
            autor.publicarContenido(contenido);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en RedSocial. Correcto.");
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en RedSocial. Incorrecto.");
    }

    //Método para eliminar un contenido

    public void eliminarContenido(Estudiante estudiante, Contenido contenido) {
        if (contenidos.buscarNodo(contenido)) {
            contenidos.eliminarNodo(contenido);
            Persistencia.guardarContenidos(contenidos, "RedSocial");
            estudiante.eliminarContenido(contenido);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarContenido en RedSocial. Correcto.");
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarContenido en RedSocial. No se eliminó el contenido.");
    }

    //Métodos para buscar un contenido por tema, autor y tipo en Red Social

    public List<Contenido> buscarContenidoPorTema(String tema) {
        return List.of();
    }
    public List<Contenido> buscarContenidoPorAutor(String autor) {
        return List.of();
    }
    public List<Contenido> buscarContenidoPorTipo(Usuario tipo) {
        return List.of();
    }

    //Método para generar un grupo de estudio en Red Social

    public GrupoEstudio generarGrupoEstudio(Estudiante base){
        return null;
    }

    //Método para obtener todos los contenidos de Red Social

    public String obtenerTodosContenidos() {
        return contenidos.mostrarLista();
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

    //Método para cargar datos de prueba en Red Social

    public RedSocial cargarDatosPrueba(){
        //red social
        RedSocial redSocial = new RedSocial("RedSocialAprendizajePrueba");

        //estudiantes
        Estudiante est1 = new Estudiante("Sofia", "Buitrago", "333");
        Estudiante est2 = new Estudiante("Luis", "Martinez", "123");
        Estudiante est3 = new Estudiante("Valeria", "Torres", "456");
        Estudiante est4 = new Estudiante("Martinez", "Luis", "676");
        Estudiante est5 = new Estudiante("Lucia", "Martinez", "789");

        //moderadores
        Moderador mod = new Moderador("Celeste", "111");

        redSocial.registrarEstudiante(est1);
        redSocial.registrarEstudiante(est2);
        redSocial.registrarEstudiante(est3);
        redSocial.registrarEstudiante(est4);
        redSocial.registrarEstudiante(est5);
        redSocial.registrarModerador(mod);

        //Grafo con conexiones manuales
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

        // Conexiones de estudiantes
        est1.agregarConexion(est2);
        est1.agregarConexion(est5);
        est1.agregarConexion(est4);

        est2.agregarConexion(est1);
        est2.agregarConexion(est3);

        est3.agregarConexion(est2);
        est3.agregarConexion(est4);

        est4.agregarConexion(est3);
        est4.agregarConexion(est5);
        est4.agregarConexion(est1);

        est5.agregarConexion(est4);
        est5.agregarConexion(est1);

        //Crear contenidos de prueba
        Contenido cont1 = new Contenido("Introducción a Java", est1, TipoContenido.PROGRAMACION);
        Contenido cont2 = new Contenido("Estructuras de Datos", est2, TipoContenido.PROGRAMACION);
        Contenido cont3 = new Contenido("Escornoplonchos", est1, TipoContenido.BIOLOGIA);

        // Publicarlos
        redSocial.publicarContenido(est1, cont1);
        redSocial.publicarContenido(est2, cont2);
        redSocial.publicarContenido(est1, cont3);

        // Crear valoraciones
        Valoracion val1 = new Valoracion(est1, cont1, 5, "Muy buen contenido, fácil de entender.");
        Valoracion val2 = new Valoracion(est2, cont1, 4, "Excelente, pero faltaron ejemplos prácticos.");
        Valoracion val3 = new Valoracion(est1, cont2, 3, "Interesante, pero un poco denso.");

        // Agregar valoraciones a los estudiantes
        est1.getValoraciones().insertarNodoInicio(val1);
        est2.getValoraciones().insertarNodoInicio(val2);
        est1.getValoraciones().insertarNodoInicio(val3);


        // Guardar valoraciones
        Persistencia.guardarValoraciones(est1.getValoraciones());
        Persistencia.guardarValoraciones(est2.getValoraciones());

        //Guardar
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

        return redSocial;
    }
}
