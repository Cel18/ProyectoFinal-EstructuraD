package proyectofinal.Modelo;

public class SolicitudAyuda implements Comparable<SolicitudAyuda>{
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
}
