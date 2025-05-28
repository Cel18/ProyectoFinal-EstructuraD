package proyectofinal.Modelo;

import java.io.Serializable;

public class SolicitudAyuda implements Comparable<SolicitudAyuda>, Serializable {
    private static final long serialVersionUID = 1L;

    private Estudiante estudiante;
    private String tema;
    private int urgencia;

    public SolicitudAyuda(Estudiante estudiante, String tema, int urgencia) {
        this.estudiante = estudiante;
        this.tema = tema;
        this.urgencia = urgencia;
    }

    public int compareTo(SolicitudAyuda otra) {
        return Integer.compare(otra.urgencia, this.urgencia);
    }

    //getters
    public Estudiante getEstudiante() {
        return estudiante;
    }

    public String getTema() {
        return tema;
    }

    public int getUrgencia() {
        return urgencia;
    }

    @Override
    public String toString() {
        return "SolicitudAyuda{" +
                "estudiante=" + estudiante +
                ", tema='" + tema + '\'' +
                ", urgencia=" + urgencia +
                '}';
    }
}
