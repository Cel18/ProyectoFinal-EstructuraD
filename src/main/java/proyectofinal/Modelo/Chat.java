package proyectofinal.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String idChat;
    private Estudiante estudiante1;
    private Estudiante estudiante2;
    private List<Mensaje> mensajes;

    public Chat(String idChat, Estudiante estudiante1, Estudiante estudiante2) {
        this.idChat = idChat;
        this.estudiante1 = estudiante1;
        this.setEstudiante2(estudiante2);
        this.mensajes = new ArrayList<>();
    }

    public void enviarMensaje(Estudiante emisor, String contenido) {
        Mensaje mensaje = new Mensaje(emisor, contenido);
        mensajes.add(mensaje);
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public Estudiante getEstudiante1() {
        return estudiante1;
    }

    public Estudiante getEstudiante2() {
        return estudiante2;
    }

    public String getIdChat() {
        return idChat;
    }

    public void setEstudiante2(Estudiante estudiante2) {
        this.estudiante2 = estudiante2;
    }
}