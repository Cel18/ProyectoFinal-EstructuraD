package proyectofinal.Utilidades;

import proyectofinal.Modelo.*;

import java.io.*;
import java.util.*;

public class Persistencia {

    //Persistencia de la lista de los contenidos
    public static void guardarContenidos(List<Contenido> contenidos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("contenidos.dat"))) {
            oos.writeObject(contenidos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Contenido> cargarContenido() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("contenidos.dat"))) {
            return (List<Contenido>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //Persistencia de la lista de los estudiantes
    public static void guardarEstudiantes(Map<String, Estudiante> estudiantes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("estudiantes.dat"))) {
            oos.writeObject(estudiantes);
            System.out.println("estudiante guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar estudiante:");
            e.printStackTrace();
        }
    }

    public static Map<String, Estudiante> cargarEstudiante() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("estudiantes.dat"))) {
            System.out.println("Estudiantes cargado desde archivo.");
            return (Map<String, Estudiante>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar estudiantes:");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    //Persistencia de la lista de los grafos de afinidad
    public static void guardarGrafosAfinidad(List<GrafoAfinidad> grafosAfinidad) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("grafosAfinidad.dat"))) {
            oos.writeObject(grafosAfinidad);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<GrafoAfinidad> cargarGrafosAfinidad() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("grafosAfinidad.dat"))) {
            return (List<GrafoAfinidad>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //Persistencia de la lista de los grupos de estudio
    public static void guardarGruposEstudio(List<GrupoEstudio> gruposEstudio) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gruposEstudio.dat"))) {
            oos.writeObject(gruposEstudio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<GrupoEstudio> cargarGruposEstudio() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gruposEstudio.dat"))) {
            return (List<GrupoEstudio>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //Persistencia de la lista de los moderadores
    public static void guardarModeradores(Map<String, Moderador> moderadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("moderadores.dat"))) {
            oos.writeObject(moderadores);
            System.out.println("moderador guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar moderador:");
            e.printStackTrace();
        }
    }

    public static Map<String, Moderador> cargarModeradores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("moderadores.dat"))) {
            System.out.println("Moderadores cargado desde archivo.");
            return (Map<String, Moderador>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar moderadores:");
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    //Persistencia de la red social
    public static void guardarRedSocial(RedSocial redSocial) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("redSocial.dat"))) {
            oos.writeObject(redSocial);
            System.out.println("Red social guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar red social:");
            e.printStackTrace();
        }
    }

    public static RedSocial cargarRedSocial() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("redSocial.dat"))) {
            System.out.println("Red social cargada desde archivo.");
            return (RedSocial) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar red social:");
            e.printStackTrace();
            return new RedSocial("");
        }
    }

    //Persistencia de la lista de las valoraciones
    public static void guardarValoraciones(List<Valoracion> valoraciones) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("valoraciones.dat"))) {
            oos.writeObject(valoraciones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Valoracion> cargarValoraciones() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("valoraciones.dat"))) {
            return (List<Valoracion>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}