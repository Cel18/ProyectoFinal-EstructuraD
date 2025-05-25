package proyectofinal.Modelo;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Edge;
import java.io.Serializable;
import java.util.*;

public class GrafoAfinidad implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Graph graph;
    private Map<String, Estudiante> mapaEstudiantes; // Para facilitar recuperación

    public GrafoAfinidad() {
        graph = new SingleGraph("Grafo de Afinidad");
        graph.setAutoCreate(true);
        graph.setStrict(false);

        graph.setAttribute("ui.stylesheet",
                "node { fill-color: deepskyblue; size: 20px; text-alignment: under; text-size: 14px; }" +
                        "edge { fill-color: lightgray; }");

        mapaEstudiantes = new HashMap<>();
    }

    public void agregarEstudiante(Estudiante e) {
        if (graph.getNode(e.getNombre()) == null) {
            Node nodo = graph.addNode(e.getNombre());
            nodo.setAttribute("ui.label", e.getNombre());
            mapaEstudiantes.put(e.getNombre(), e);
        }
    }

    public void conectar(Estudiante e1, Estudiante e2) {
        String id1 = e1.getNombre();
        String id2 = e2.getNombre();
        String edgeId = id1 + "-" + id2;

        if (graph.getEdge(edgeId) == null && graph.getEdge(id2 + "-" + id1) == null) {
            graph.addEdge(edgeId, id1, id2);
        }
    }

    public List<Estudiante> obtenerVecinos(Estudiante e) {
        List<Estudiante> vecinos = new ArrayList<>();
        Node nodo = graph.getNode(e.getNombre());

        if (nodo != null) {
            for (Edge edge : nodo.edges().toList()) {
                Node otro = edge.getOpposite(nodo);
                Estudiante vecino = mapaEstudiantes.get(otro.getId());
                if (vecino != null) {
                    vecinos.add(vecino);
                }
            }
        }
        return vecinos;
    }

    public List<Estudiante> obtenerCaminoMasCorto(Estudiante e1, Estudiante e2) {
        //dijkstra o como se llame
        return List.of();
    }

    public List<Estudiante> sugerirEstudiante(Estudiante e) {
        return List.of();
    }

    public List<GrupoEstudio> detectarGrupos() {
        return List.of();
    }

    public Graph getGraph() {
        return graph;
    }

    public void mostrarGrafo() {
        for (Node nodo : graph) {
            System.out.print(nodo.getId() + " está conectado con: ");
            for (Edge arista : nodo.edges().toList()) {
                Node otro = arista.getOpposite(nodo);
                System.out.print(otro.getId() + ", ");
            }
            System.out.println();
        }
    }

    // Método para reestablecer el grafo si fue deserializado (transient)
    public void reconstruirGrafoSiEsNecesario() {
        if (graph == null) {
            graph = new SingleGraph("Grafo de Afinidad");
            graph.setAutoCreate(true);
            graph.setStrict(false);
            graph.setAttribute("ui.stylesheet",
                    "node { fill-color: deepskyblue; size: 20px; text-alignment: under; text-size: 14px; }" +
                            "edge { fill-color: lightgray; }");

            for (Estudiante e : mapaEstudiantes.values()) {
                agregarEstudiante(e);
            }
        }
    }
}
