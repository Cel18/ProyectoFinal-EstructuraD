package proyectofinal.Test;

import proyectofinal.Modelo.*;

public class TestValoraciones {
    public static void main(String[] args) {
        // Crear estudiantes
        Estudiante e1 = new Estudiante("Ana", "López", "1234");
        Estudiante e2 = new Estudiante("Luis", "García", "abcd");

        // Crear contenido
        Contenido c1 = new Contenido("Polinomios", e1, TipoContenido.MATEMATICAS);
        Contenido c2 = new Contenido("Python es mejor que Java", e2, TipoContenido.PROGRAMACION);
        Contenido c3 = new Contenido("La vida es bella", e1, TipoContenido.BIOLOGIA);

        // Publicar contenido
        e1.publicarContenido(c1);
        e2.publicarContenido(c2);
        e1.publicarContenido(c3);

        // Valorar contenido del otro estudiante
        e1.valorarContenido(c2, 4, "Muy claro y bien explicado.");
        e2.valorarContenido(c1, 5, "Excelente material.");
        e2.valorarContenido(c3, 1, "No.");

        // Buscar contenido por tema
        NodoContenido<Contenido> encontrado = e1.buscarContenidoTema("Polinomios");
        if (encontrado != null) {
            System.out.println("Contenido encontrado: " + encontrado.getContenido());
        } else {
            System.out.println("Contenido no encontrado.");
        }

        NodoContenido<Contenido> encontrado2 = e1.buscarContenidoTema("Python es peor que Java");
        if (encontrado2 != null) {
            System.out.println("Contenido encontrado: " + encontrado.getContenido());
        } else {
            System.out.println("Contenido no encontrado.");
        }

        // Mostrar valoraciones de c1
        NodoContenido<Valoracion> actual = c1.getValoraciones().getInicial();
        while (actual != null) {
            Valoracion v = actual.getContenido();
            System.out.println("Valoración de: " + v.getEstudiante().getNombreCompleto() +
                    " - Puntuación: " + v.getPuntuacion() +
                    " - Comentario: " + v.getComentario());
            actual = actual.getDerecho();
        }

        // Promedio de valoraciones de e1
        System.out.println("Promedio valoraciones de " + e1.getNombreCompleto() + ": " +
                e1.getPromedioValoraciones());

        // Mostrar conexiones
        System.out.println("Conexiones de " + e1.getNombreCompleto() + ":");
        NodoContenido<Estudiante> con = e1.getConexiones().getInicial();
        while (con != null) {
            System.out.println("- " + con.getContenido().getNombreCompleto());
            con = con.getDerecho();
        }
    }
}