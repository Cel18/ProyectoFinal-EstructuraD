package proyectofinal.Test;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import proyectofinal.Modelo.*;

public class GrafoVisual {
    public static void main(String[] args) {
        // Crear estudiantes de prueba
        Estudiante e1 = new Estudiante("Ana", "García", "123");
        Estudiante e2 = new Estudiante("Luis", "Pérez", "123");
        Estudiante e3 = new Estudiante("Sofía", "López", "123");
        Estudiante e4 = new Estudiante("Carlos", "Ramírez", "123");

        // Crear grafo de afinidad
        GrafoAfinidad grafo = new GrafoAfinidad();
        grafo.agregarEstudiante(e1);
        grafo.agregarEstudiante(e2);
        grafo.agregarEstudiante(e3);
        grafo.agregarEstudiante(e4);

        grafo.conectar(e1, e2);
        grafo.conectar(e2, e3);
        grafo.conectar(e3, e4);
        grafo.conectar(e1, e4);

        // Visualizar con GraphStream
        System.setProperty("org.graphstream.ui", "swing");
        Graph g = new SingleGraph("Red de Afinidad");
        g.setAttribute("ui.stylesheet", styleSheet);
        g.setAttribute("ui.quality");
        g.setAttribute("ui.antialias");

        // Agregar nodos
        for (Estudiante e : grafo.obtenerAdyacencias().keySet()) {
            String id = e.getNombre() + "_" + e.getApellido(); // ID sin espacios
            Node nodo = g.addNode(id);
            nodo.setAttribute("ui.label", e.getNombreCompleto()); // Etiqueta con espacios
        }

        // Agregar aristas
        for (Estudiante origen : grafo.obtenerAdyacencias().keySet()) {
            for (Estudiante destino : grafo.obtenerVecinos(origen)) {
                String id1 = origen.getNombre() + "_" + origen.getApellido();
                String id2 = destino.getNombre() + "_" + destino.getApellido();
                String edgeId = id1 + "-" + id2;

                if (g.getEdge(edgeId) == null && g.getEdge(id2 + "-" + id1) == null) {
                    g.addEdge(edgeId, id1, id2);
                }
            }
        }

        g.display();
    }

    // Estilo visual para el grafo
    protected static String styleSheet =
            """
            graph {
                padding: 50px;
                fill-color: #ACDAD8;
            }
            node {
                fill-color: #24789C;
                size: 30px;
                text-size: 16px;
                text-color: black;
                text-style: bold;
                text-alignment: above;
                stroke-mode: plain;
                stroke-color: #FFFFFF;
                shape: circle;
            }
    
            edge {
                fill-color: #889DC1;
                size: 3px;
            }
    
            graph {
                padding: 50px;
            }
            """;
}