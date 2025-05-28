package proyectofinal.Modelo;

import java.io.Serial;
import java.io.Serializable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Valoracion implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Estudiante estudiante;
    private Contenido contenido;
    private int puntuacion;
    private String comentario;

    public Valoracion(Estudiante estudiante, Contenido contenido, int puntuacion, String comentario) {
        this.estudiante = estudiante;
        this.contenido = contenido;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Valoracion that)) return false;
        return Objects.equals(estudiante, that.estudiante) &&
                Objects.equals(contenido, that.contenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudiante, contenido);
    }

    @Override
    public String toString() {
        return "Puntuación: " + puntuacion + ". Realizada por " +
                estudiante.getNombreCompleto() + " en "
                + contenido.getTema() + ".\n" + comentario + "\n";
    }
}