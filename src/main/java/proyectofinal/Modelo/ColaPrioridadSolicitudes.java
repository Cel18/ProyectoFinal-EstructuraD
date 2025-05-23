package proyectofinal.Modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ColaPrioridadSolicitudes implements Serializable {

    private LinkedList<SolicitudAyuda> cola;

    public ColaPrioridadSolicitudes() {
        this.cola = new LinkedList<>();
    }

    // Agrega la solicitud ordenándola por prioridad (mayor urgencia primero)
    public void agregarSolicitud(SolicitudAyuda solicitud) {
        if (cola.isEmpty()) {
            cola.add(solicitud);
            return;
        }

        int i = 0;
        while (i < cola.size() && solicitud.getUrgencia() <= cola.get(i).getUrgencia()) {
            i++;
        }

        cola.add(i, solicitud); // Inserta en la posición correcta
    }

    // Retorna sin eliminar la solicitud con mayor prioridad
    public SolicitudAyuda obtenerSolicitudPrioritaria() {
        return cola.isEmpty() ? null : cola.getFirst();
    }

    // Elimina la solicitud con mayor prioridad
    public void eliminarSolicitudPrioritaria() {
        if (!cola.isEmpty()) {
            cola.removeFirst();
        }
    }

    // Elimina una solicitud específica
    public void eliminarSolicitud(SolicitudAyuda solicitud) {
        cola.remove(solicitud);
    }

    // Verifica si la cola está vacía
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    // Retorna todas las solicitudes
    public List<SolicitudAyuda> obtenerTodasLasSolicitudes() {
        return new LinkedList<>(cola); // Evita exponer la lista original
    }

    // Para debug o impresión
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cola de Solicitudes:\n");
        for (SolicitudAyuda s : cola) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }
}