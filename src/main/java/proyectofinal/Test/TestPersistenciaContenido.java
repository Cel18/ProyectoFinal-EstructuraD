package proyectofinal.Test;

import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

public class TestPersistenciaContenido {

    public static void main(String[] args) {
        RedSocial redSocial = new RedSocial("Dystopia");

        Estudiante e1 = new Estudiante("Celeste", "Buitrago", "pato12345");
        Estudiante e2 = new Estudiante("Juan", "García", "linux123");

        Contenido c1 = new Contenido("Mickey 17",e1, TipoContenido.LECTOESCRITURA);
        Contenido c2 = new Contenido("Pim pam trucu trucu",e2, TipoContenido.BIOLOGIA);
        Contenido c3 = new Contenido("Mi Estrella Blanca",e1, TipoContenido.LECTOESCRITURA);

        redSocial.registrarEstudiante(e1);
        redSocial.registrarEstudiante(e2);

        redSocial.publicarContenido(e1, c1);
        redSocial.publicarContenido(e2, c3);
        redSocial.publicarContenido(e1, c2);

        mostrarPersistencia();
    }

    public static void mostrarPersistencia() {
        ListaEnlazada<Contenido> contenidosCargados = Persistencia.cargarContenido("RedSocial");

        System.out.println("=== Contenidos Persistidos ===");

        for (Contenido contenido : contenidosCargados) {
            System.out.println(contenido);
        }
    }
}
