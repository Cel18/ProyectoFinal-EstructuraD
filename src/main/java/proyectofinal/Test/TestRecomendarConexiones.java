package proyectofinal.Test;

import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

public class TestRecomendarConexiones {

    public static void main(String[] args) {
        //Crear red social
        //RedSocial redSocial = new RedSocial("Flamingo");
        RedSocial redSocial = Persistencia.cargarRedSocial();

        //Crear estudiantes y moderador
        Estudiante e1 = new Estudiante("Lenovo", "18", "contrasenia");
        Estudiante e2 = new Estudiante("Ophelia", "Orlando", "12345");
        Estudiante e3 = new Estudiante("Cebolla", "Francesa", "soyelmejor");
        Estudiante e4 = new Estudiante("Centinela", "Hornet", "dinosaurio");
        Estudiante e5 = new Estudiante("Sofía", "Ramírez", "pato12345");
        Estudiante e6 = new Estudiante("Nate", "Ruess", "fun.");

        Moderador m1 = new Moderador("Importante", "13579");

        //Registrar los estudiantes
        redSocial.registrarEstudiante(e1);
        redSocial.registrarEstudiante(e2);
        redSocial.registrarEstudiante(e3);
        redSocial.registrarEstudiante(e4);
        redSocial.registrarEstudiante(e5);
        redSocial.registrarEstudiante(e6);

        System.out.println(redSocial.getEstudiantes());

        //Generar conexiones

        e1.agregarConexion(e2);
        e1.agregarConexion(e3);
        e2.agregarConexion(e1);
        e2.agregarConexion(e4);
        e4.agregarConexion(e1);
        e5.agregarConexion(e2);

        e6.agregarConexion(e5);
        e6.agregarConexion(e1);

        System.out.println((redSocial.recomendarCompaneros(e6)).mostrarLista());
    }
}
