package proyectofinal.Test;

import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

public class TestColaDePrioridad {

    public static void main(String[] args) {
        //Crear red social
        //RedSocial redSocial = new RedSocial("Nombre serio");
        RedSocial redSocial = Persistencia.cargarRedSocial();

        //Crear estudiantes
        Estudiante e1 = new Estudiante("Oso", "Polar", "123");
        Estudiante e2 = new Estudiante("Perro", "Rabioso", "123");
        Estudiante e3 = new Estudiante("Pajaro", "Carpintero", "123");

        //Crear solicitudes de ayuda
        SolicitudAyuda sy1 = new SolicitudAyuda(e2, "No me sirve el sql.", 5);
        SolicitudAyuda sy2 = new SolicitudAyuda(e3, "El perro está asustándome al privado.", 7);

        //Guardar las solicitudes de ayuda
        ColaPrioridadSolicitudes cps = new ColaPrioridadSolicitudes();

        //Edición de las solicitudes
        redSocial.agregarSolicitudAyuda(sy1);
        redSocial.agregarSolicitudAyuda(sy2);

        //redSocial.eliminarSolicitudAyuda(sy2);

        System.out.println(redSocial.getColaSolicitudes().obtenerTodasLasSolicitudes().toString());
    }
}
