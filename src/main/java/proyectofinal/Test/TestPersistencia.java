package proyectofinal.Test;

import proyectofinal.Modelo.Estudiante;
import proyectofinal.Utilidades.Persistencia;

import java.util.HashMap;
import java.util.Map;

public class TestPersistencia {
    public static void main(String[] args) {

        // Crear estudiantes de prueba
        Estudiante est1 = new Estudiante("Juan", "Pérez", "E001");
        Estudiante est2 = new Estudiante("Ana", "Gómez", "E002");

        // Guardarlos en un mapa
        Map<String, Estudiante> estudiantes = new HashMap<>();
        estudiantes.put(est1.getId(), est1);
        estudiantes.put(est2.getId(), est2);

        // Guardar en archivo
        Persistencia.guardarEstudiantesMapa(estudiantes);
        System.out.println("Estudiantes guardados exitosamente.\n");

        // Cargar desde archivo
        Map<String, Estudiante> estudiantesCargados = Persistencia.cargarEstudianteMapa();
        System.out.println("Estudiantes cargados desde archivo:\n");

        for (Estudiante e : estudiantesCargados.values()) {
            System.out.println("Nombre: " + e.getNombre() + " " + e.getApellido() + ", ID: " + e.getId());
        }
    }
}
