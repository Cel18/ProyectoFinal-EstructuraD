package proyectofinal.Test;

import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

import java.util.GregorianCalendar;


public class TestGrupoEstudio {

    public static void main(String[] args) {
        //Crear red social
        //RedSocial redSocial = new RedSocial("Sociedad Seria");
        RedSocial redSocial = Persistencia.cargarRedSocial();

        //Crear estudiantes y contenido
        Estudiante e1 = new Estudiante("Lenovo", "18", "contrasenia");
        Estudiante e2 = new Estudiante("Ophelia", "Orlando", "12345");
        Estudiante e3 = new Estudiante("Cebolla", "Francesa", "soyelmejor");

        Contenido c1 = new Contenido("El perro de Alcibíades", e1, TipoContenido.LECTOESCRITURA);

        //Crear moderador
        Moderador m1 = new Moderador("Señor Serio", "123");

        //Registrar estudiantes y moderador
        redSocial.registrarModerador(m1);

        redSocial.registrarEstudiante(e1);
        m1.registrarEstudiante(e2);
        m1.registrarEstudiante(e3);

        //Publicar contenido

        redSocial.publicarContenido(e1, c1);

        //Agregar estudiante a un grupo de estudio

        redSocial.ingresarEstudianteAGrupoEstudio(redSocial.buscarGrupoEstudioPorNombre("Grupo_LECTOESCRITURA"), e1);

        System.out.println(redSocial.getGrupoEstudios().mostrarLista());
        //mostrarPersistencia();
    }

    /*
    public static void mostrarPersistencia() {
        ListaEnlazada<GrupoEstudio> gruposEstudioCargados = Persistencia.cargarGruposEstudio("RedSocial");

        System.out.println("=== Contenidos Persistidos ===");

        for (GrupoEstudio grupoEstudio : gruposEstudioCargados) {
            System.out.println(grupoEstudio);
        }
    }
    */
}
