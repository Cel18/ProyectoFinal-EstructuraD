package proyectofinal.Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoAfinidad {
    private Map<Estudiante, List<Estudiante>> adyacencias;

    public GrafoAfinidad() {
        adyacencias = new HashMap<>();
    }

    public void agregarEstudiante(Estudiante e){
        adyacencias.putIfAbsent(e, new ArrayList<>());
    }

    public void conectar(Estudiante e1, Estudiante e2){
        adyacencias.get(e1).add(e2);
        adyacencias.get(e2).add(e1);
    }

    public List<Estudiante> obtenerVecinos(Estudiante e){
        return adyacencias.getOrDefault(e, new ArrayList<>());
    }
}
