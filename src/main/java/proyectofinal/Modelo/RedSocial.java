package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;
import proyectofinal.Utilidades.Utilidades;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class RedSocial implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nombre;
    private Map<String, Estudiante> estudiantes;
    private Map<String, Moderador> moderadores;
    private ListaEnlazada<Contenido> contenidos;
    private GrafoAfinidad grafo;
    private ColaPrioridadSolicitudes colaSolicitudes;
    private ListaEnlazada<GrupoEstudio> grupoEstudios;
    private Estudiante estudianteActivo; //estudiante loggeado
    private Moderador moderadorActivo;

    //Constructor de la clase Red Social

    public RedSocial(String nombre) {
        this.nombre = nombre;
        this.estudiantes = new HashMap<>();
        this.moderadores = new HashMap<>();
        this.contenidos = Persistencia.cargarContenido("RedSocial");
        this.grafo = new GrafoAfinidad();
        this.colaSolicitudes = new ColaPrioridadSolicitudes();
        this.grupoEstudios = Persistencia.cargarGruposEstudio("RedSocial");
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

            GrupoEstudio grupo = new GrupoEstudio("Grupo_" + contenido.getTipo().toString());
                if (!grupoEstudios.buscarNodo(grupo)) {
                    generarGrupoEstudio(grupo);
                }
            Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en RedSocial. Correcto.");
        } else {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método publicarContenido en RedSocial. Incorrecto.");
        }
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

    public ListaEnlazada<Contenido> buscarContenidosPorTema(String tema) {
        NodoContenido<Contenido> nodoRecorrer = contenidos.getInicial();
        ListaEnlazada<Contenido> listaContenidos = new ListaEnlazada<>();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getTema().equals(tema)) {
                listaContenidos.insertarNodoInicio(nodoRecorrer.getContenido());
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoPorTema en RedSocial. Correcto.");
        return listaContenidos;
    }

    public ListaEnlazada<Contenido> buscarContenidosPorAutor(Estudiante autor) {
        NodoContenido<Contenido> nodoRecorrer = contenidos.getInicial();
        ListaEnlazada<Contenido> listaContenidos = new ListaEnlazada<>();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getAutor().equals(autor)) {
                listaContenidos.insertarNodoInicio(nodoRecorrer.getContenido());
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoPorAutor en RedSocial. Correcto.");
        return listaContenidos;
    }

    public ListaEnlazada<Contenido> buscarContenidosPorTipo(TipoContenido tipo) {
        NodoContenido<Contenido> nodoRecorrer = contenidos.getInicial();
        ListaEnlazada<Contenido> listaContenidos = new ListaEnlazada<>();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getTipo().equals(tipo)) {
                listaContenidos.insertarNodoInicio(nodoRecorrer.getContenido());
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarContenidoPorTipo en RedSocial. Correcto.");
        return listaContenidos;
    }

    //Método para generar un grupo de estudio en Red Social

    public void generarGrupoEstudio(GrupoEstudio grupoEstudio) {
        if (!grupoEstudios.buscarNodo(grupoEstudio)) {
            grupoEstudios.insertarNodoInicio(grupoEstudio);
            Persistencia.guardarGruposEstudio(grupoEstudios, "");
            Utilidades.getInstance().escribirLog(Level.INFO, "Método generarGrupoEstudio en RedSocial. Correcto.");
            return;
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método generarGrupoEstudio en RedSocial. No se generó el grupo de estudio.");
    }

    //Método para agregar un estudiante a un Grupo de Estudio

    public void ingresarEstudianteAGrupoEstudio(GrupoEstudio grupoEstudio, Estudiante estudiante) {
        if (buscarGrupoEstudio(grupoEstudio)) {
            grupoEstudio.agregarEstudiante(estudiante);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método ingresarEstudianteAGrupoEstudio en RedSocial. Correcto.");
            return;
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método ingresarEstudianteAGrupoEstudio en RedSocial. Incorrecto.");
    }

    public boolean buscarGrupoEstudio(GrupoEstudio grupoEstudio) {
        NodoContenido<GrupoEstudio> nodoRecorrer = grupoEstudios.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getNombre().equals(grupoEstudio.getNombre())) {
                Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarGrupoEstudio en RedSocial. Correcto.");
                return true;
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarGrupoEstudio en RedSocial. No se encontró el grupo de estudio.");
        return false;
    }

    //Método para obtener todos los contenidos de Red Social

    public String obtenerTodosContenidos() {
        return contenidos.mostrarLista();
    }

    //Getters and setters

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

    public ListaEnlazada<GrupoEstudio> getGrupoEstudios() {
        return grupoEstudios;
    }

    public void setGrupoEstudios(ListaEnlazada<GrupoEstudio> grupoEstudios) {
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

        // Agregar valoraciones a los contenidos
        cont1.getValoraciones().insertarNodoInicio(val1);
        cont1.getValoraciones().insertarNodoInicio(val2);
        cont2.getValoraciones().insertarNodoInicio(val3);

        // Guardar valoraciones
        Persistencia.guardarValoraciones(est1.getValoraciones(), est1.getNombreCompleto());
        Persistencia.guardarValoraciones(est2.getValoraciones(), est2.getNombreCompleto());

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

        /*verificarArchivoContenidos(est1.getNombreCompleto());
        verificarArchivoContenidos(est2.getNombreCompleto());

        ListaEnlazada<Contenido> listaCargada = Persistencia.cargarContenido(est1.getNombreCompleto());
        est1.setContenidosPublicados(listaCargada);

        System.out.println("Contenidos cargados para " + est1.getNombreCompleto() + ":");
        for (Contenido c : est1.getContenidosPublicados()) {
            System.out.println("- " + c);
        }*/

        return redSocial;
    }

    /*public static void verificarArchivoContenidos(String nombreEstudiante) {
        File archivo = new File("contenidos_" + nombreEstudiante + ".dat");
        if (archivo.exists()) {
            System.out.println("Archivo contenidos_" + nombreEstudiante + ".dat existe.");
            System.out.println("Tamaño (bytes): " + archivo.length());
            System.out.println("Última modificación: " + new java.util.Date(archivo.lastModified()));
        } else {
            System.out.println("Archivo contenidos_" + nombreEstudiante + ".dat NO existe.");
        }
    }*/

}
