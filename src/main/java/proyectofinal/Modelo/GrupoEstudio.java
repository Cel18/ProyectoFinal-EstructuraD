package proyectofinal.Modelo;

import proyectofinal.Utilidades.Persistencia;
import proyectofinal.Utilidades.Utilidades;

import java.io.Serializable;
import java.util.logging.Level;

public class GrupoEstudio implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nombre;
    private ListaEnlazada<Estudiante> integrantes;

    public GrupoEstudio(String nombre) {
        this.nombre = nombre;
        this.integrantes = new ListaEnlazada<>();
    }

    //Método para agregar un estudiante al Grupo Estudio

    public void agregarEstudiante(Estudiante estudiante) {
        if (!integrantes.buscarNodo(estudiante)) {
            integrantes.insertarNodoInicio(estudiante);
            Persistencia.guardarEstudiante(estudiante, getNombre());
            Utilidades.getInstance().escribirLog(Level.INFO, "Método agregarEstudiante en GrupoEstudio. Correcto.");
            return;
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método agregarEstudiante en GrupoEstudio. No se agregó el estudiante.");
    }

    //Método para eliminar un estudiante del Grupo de estudio

    public void eliminarEstudiante(Estudiante estudiante) {
        NodoContenido<Estudiante> nodoRecorrer = integrantes.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().equals(estudiante)) {
                integrantes.eliminarNodo(estudiante);
                Persistencia.guardarConexiones(integrantes, getNombre());
                Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarEstudiante en GrupoEstudio. Correcto.");
                return;
            }
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarEstudiante en GrupoEstudio. No se eliminó el estudiante.");
    }

    //Método para buscar un estudiante por el nombre

    public Estudiante buscarEstudiantePorNombre(String nombre) {
        NodoContenido<Estudiante> nodoRecorrer = integrantes.getInicial();

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().getNombre().equals(nombre)) {
                Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarEstudiantePorNombre en GrupoEstudio. Correcto.");
                return nodoRecorrer.getContenido();
            }

            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarEstudiantePorNombre en GrupoEstudio. No se eliminó el estudiante.");
        return null;
    }

    public ListaEnlazada<Estudiante> getIntegrantes() {
        return integrantes;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
