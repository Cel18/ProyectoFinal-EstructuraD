package proyectofinal.Test;

import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

public class TestEstudiante {

    public static void main(String[] args) {
        //Crear Red Social
        RedSocial redSocial = new RedSocial("4chan");
        Persistencia.guardarRedSocial(redSocial);

        //Crear Moderador
        Moderador m1 = new Moderador("nombre", "123");

        //Crear Estudiantes
        Estudiante e1 = new Estudiante("Celeste", "Ramírez", "12345");
        Estudiante e2 = new Estudiante("Juan", "Ramírez", "12345");
        Estudiante e3 = new Estudiante("Juan", "Nieto", "12345");

        //Crear Contenidos
        Contenido c1 = new Contenido("Cálculo Integral y Otras Historias del Terror", e3, TipoContenido.MATEMATICAS);
        Contenido c2 = new Contenido("Mi Estrella Blanca", e2, TipoContenido.LECTOESCRITURA);
        Contenido c3 = new Contenido("Don Quijote de la Seriedad", e1, TipoContenido.LECTOESCRITURA);
        Contenido c4 = new Contenido("La Divina Seriedad", e2, TipoContenido.LECTOESCRITURA);

        //Registrar estudiantes y moderador
        redSocial.registrarEstudiante(e1);
        redSocial.registrarEstudiante(e2);
        redSocial.registrarEstudiante(e3);

        redSocial.registrarModerador(m1);

        //Publicar contenidos
        redSocial.publicarContenido(e1, c3);
        redSocial.publicarContenido(e2, c2);
        redSocial.publicarContenido(e2, c4);
        redSocial.publicarContenido(e3, c1);

        //Eliminar contenidos

        redSocial.eliminarContenido(e2, c2);

        //Mostrar Contenido
        System.out.println(redSocial.obtenerTodosContenidos());
    }
}
