package proyectofinal.Utilidades;

import proyectofinal.Modelo.*;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class Persistencia {

    //Persistencia de la lista de los contenidos
    public static void guardarContenidos(ListaEnlazada<Contenido> contenidos, String nombre) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contenidos_" + nombre + ".dat"))) {
            oos.writeObject(contenidos);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarContenidos en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarContenidos en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }

    public static ListaEnlazada<Contenido> cargarContenido(String nombre) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contenidos_" + nombre + ".dat"))) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método cargarContenido en Persistencia. Correcto.");
            return (ListaEnlazada<Contenido>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarContenidos en Persistencia. Incorrecto.");
            e.printStackTrace();
            return new ListaEnlazada<>();
        }
    }

    //Persistencia del mapa de los estudiantes

    public static void guardarEstudiantesMapa(Map<String, Estudiante> estudiantes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estudiantes_map.dat"))) {
            oos.writeObject(estudiantes);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarEstudiantesMapa en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarEstudiantesMapa en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }

    public static Map<String, Estudiante> cargarEstudiantesMapa() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estudiantes_map.dat"))) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método cargarEstudiantesMapa en Persistencia. Correcto.");
            return (Map<String, Estudiante>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método cargarEstudiantesMapa en Persistencia. Incorrecto.");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    //Persistencia de conexión entre dos estudiantes

    public static void guardarEstudiante(Estudiante estudiante) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estudiantes_lista.dat"))) {
            oos.writeObject(estudiante);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarEstudiante en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarEstudiante en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }

    public static Estudiante cargarEstudiante(String nombre) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estudiante_" + nombre + ".dat"))) {
            return (Estudiante) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Persistencia de la lista de conexiones

    public static void guardarConexiones(ListaEnlazada<Estudiante> conexiones) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estudiantes_lista.dat"))) {
            oos.writeObject(conexiones);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarConexiones en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarConexiones en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }
    //Persistencia de la lista de los grafos de afinidad

    public static void guardarGrafosAfinidad(List<GrafoAfinidad> grafosAfinidad) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("grafosAfinidad.dat"))) {
            oos.writeObject(grafosAfinidad);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarGrafosAfinidad en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarGrafoAfinidad en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }

    public static List<GrafoAfinidad> cargarGrafosAfinidad() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("grafosAfinidad.dat"))) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método cargarGrafosAfinidad en Persistencia. Correcto.");
            return (List<GrafoAfinidad>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método cargarGrafosAfinidad en Persistencia. Correcto.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //Persistencia de la lista de los grupos de estudio

    public static void guardarGruposEstudio(List<GrupoEstudio> gruposEstudio) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gruposEstudio.dat"))) {
            oos.writeObject(gruposEstudio);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarGruposEstudio en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarGruposEstudio en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }

    public static List<GrupoEstudio> cargarGruposEstudio() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gruposEstudio.dat"))) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método cargarGruposEstudio en Persistencia. Correcto.");
            return (List<GrupoEstudio>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método cargarGruposEstudio en Persistencia. Incorrecto.");
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //Persistencia de la lista de los moderadores

    public static void guardarModeradores(Map<String, Moderador> moderadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("moderadores.dat"))) {
            oos.writeObject(moderadores);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarModeradores en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarModeradores en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }

    public static Map<String, Moderador> cargarModeradores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("moderadores.dat"))) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método cargarModeradores en Persistencia. Correcto.");
            return (Map<String, Moderador>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método cargarModeradores en Persistencia. Incorrecto.");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    //Persistencia de la red social

    public static void guardarRedSocial(RedSocial redSocial) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("redSocial.dat"))) {
            oos.writeObject(redSocial);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarRedSocial en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarRedSocial en Persistencia. Incorrecto.");
            e.printStackTrace();
        }
    }

    public static RedSocial cargarRedSocial() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("redSocial.dat"))) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método cargarRedSocial en Persistencia. Correcto.");
            return (RedSocial) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método cargarRedSocial en Persistencia. Incorrecto.");
            e.printStackTrace();
            return new RedSocial("");
        }
    }

    //Persistencia de la lista de las valoraciones

    public static void guardarValoraciones(ListaEnlazada<Valoracion> valoraciones) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("valoraciones.dat"))) {
            oos.writeObject(valoraciones);
            Utilidades.getInstance().escribirLog(Level.INFO, "Método guardarValoraciones en Persistencia. Correcto.");
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método guardarValoraciones en Persistencia. Correcto.");
            e.printStackTrace();
        }
    }

    public static ListaEnlazada<Valoracion> cargarValoraciones() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("valoraciones.dat"))) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método cargarValoraciones en Persistencia. Correcto.");
            return (ListaEnlazada<Valoracion>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método cargarValoraciones en Persistencia. Incorrecto.");
            e.printStackTrace();
            return new ListaEnlazada<>();
        }
    }
}