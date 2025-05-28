package proyectofinal.Modelo;

import proyectofinal.Utilidades.*;

import java.io.Serializable;
import java.util.logging.Level;

public class Moderador extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private RedSocial redSocial;

    //Constructor de la clase Moderador

    public Moderador(String nombre, String contrasena) {
        super(nombre, contrasena);
        this.redSocial = Persistencia.cargarRedSocial();
    }

    //Método para agregar un estudiante a la Red Social

    public void registrarEstudiante(Estudiante estudiante) {
        redSocial.registrarEstudiante(estudiante);
        Utilidades.getInstance().escribirLog(Level.INFO, "Método registrarEstudiante en Moderador. Correcto.");
    }

    //Método para eliminar un estudiante de la Red Social

    public void eliminarEstudiante(Estudiante estudiante) {
        redSocial.eliminarEstudiante(estudiante);
        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarEstudiante en Moderador. Correcto.");
    }

    //Método para buscar un estudiante por el nombre de la Red Social

    public Estudiante buscarEstudiantePorNombre(String nombre) {
        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarEstudiantePorNombre en Moderador. Correcto.");
        return redSocial.buscarEstudiante(nombre);
    }

    public void generarReporteContenidos(){}

    public void analizarGrafoAfinidad(GrafoAfinidad grafo){}

    @Override
    public String toString() {
        return nombre;
    }
}