package proyectofinal.Modelo;

import java.time.LocalDateTime;

public class Mensaje {
    private Estudiante emisor;
    private String contenido;
    private LocalDateTime fechaHora;

    public Mensaje(Estudiante emisor, String contenido) {
        this.emisor = emisor;
        this.contenido = contenido;
        this.fechaHora = LocalDateTime.now();
    }

    public Estudiante getEmisor() {
        return emisor;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return "[" + fechaHora.toLocalTime() + "] " + ": " + contenido;
    }
}
