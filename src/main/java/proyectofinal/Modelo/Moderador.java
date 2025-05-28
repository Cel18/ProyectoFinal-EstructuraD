package proyectofinal.Modelo;

import java.io.Serializable;

public class Moderador extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    //Constructor de la clase Moderador

    public Moderador(String nombre, String contrasena) {
        super(nombre, contrasena);
    }

    //Método para agregar un estudiante a la Red Social

    public void agregarEstudiante() {

    }

    //Método para eliminar un estudiante de la Red Social

    public void eliminarEstudiante() {

    }

    //Método para buscar un estudiante por el nombre de la Red Social

    public Estudiante buscarEstudiantePorNombre() {

        return null;
    }

    public void generarReporteContenidos(){}

    public void analizarGrafoAfinidad(GrafoAfinidad grafo){}

    @Override
    public String toString() {
        return nombre;
    }
}