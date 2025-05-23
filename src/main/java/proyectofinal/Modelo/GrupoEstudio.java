package proyectofinal.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GrupoEstudio implements Serializable {
    private static final long serialVersionUID = 1L;
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
