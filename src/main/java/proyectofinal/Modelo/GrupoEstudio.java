package proyectofinal.Modelo;

import java.util.ArrayList;
import java.util.List;

public class GrupoEstudio {
    private List<Estudiante> integrantes;

    public GrupoEstudio() {
        integrantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiante estudiante) {
        if (!integrantes.contains(estudiante)) {
            integrantes.add(estudiante);
        }
    }

    public List<Estudiante> getIntegrantes() {
        return integrantes;
    }
}
