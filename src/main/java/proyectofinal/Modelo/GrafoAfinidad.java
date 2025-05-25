package proyectofinal.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrafoAfinidad implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public List<Estudiante> obtenerCaminoMasCorto(Estudiante e1, Estudiante e2){
        return List.of();
    }
    public List<Estudiante> sugerirEstudiante(Estudiante e){
        return List.of();
    }
    public List<GrupoEstudio> detectarGrupos(){
        return List.of();
    }

    public Map<Estudiante, List<Estudiante>> obtenerAdyacencias() {
        return adyacencias;
    }
    public void mostrarGrafo() {
        for (Estudiante e : adyacencias.keySet()) {
            System.out.print(e.getNombreCompleto() + " está conectado con: ");
            for (Estudiante vecino : adyacencias.get(e)) {
                System.out.print(vecino.getNombreCompleto() + ", ");
            }
            System.out.println();
        }
    }
}
