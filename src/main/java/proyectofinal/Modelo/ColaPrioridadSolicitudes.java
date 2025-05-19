package proyectofinal.Modelo;

import java.util.PriorityQueue;

public class ColaPrioridadSolicitudes {
    private PriorityQueue<SolicitudAyuda> cola;

    public ColaPrioridadSolicitudes() {
        cola =  new PriorityQueue<>();
    }

    public void agregarSolicitud(SolicitudAyuda solicitud) {
        cola.add(solicitud);
    }

    public SolicitudAyuda atender() {
        return cola.poll();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }
}
