package proyectofinal.Modelo;

public class Valoracion {
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

    //getters and setters
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

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
