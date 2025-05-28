package proyectofinal.Modelo;

import proyectofinal.Utilidades.Utilidades;

import java.io.Serializable;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Level;

public class ColaPrioridadSolicitudes implements Serializable {

    private PriorityQueue<SolicitudAyuda> cola;

    public ColaPrioridadSolicitudes() {
        this.cola = new PriorityQueue<>(Comparator.comparingInt(SolicitudAyuda::getUrgencia).reversed());
    }

    // Agrega la solicitud ordenándola por prioridad (mayor urgencia primero)

    public void agregarSolicitud(SolicitudAyuda solicitud) {
        cola.offer(solicitud);
        Utilidades.getInstance().escribirLog(Level.INFO, "Método agregarSolicitud en ColaPrioridadSolicitudes. Correcto.");
    }

    // Retorna sin eliminar la solicitud con mayor prioridad

    public SolicitudAyuda obtenerSolicitudPrioritaria() {
        Utilidades.getInstance().escribirLog(Level.INFO, "Método obtenerSolicitudPrioridad en ColaPrioridadSolicitudes. Correcto.");
        return cola.peek();
    }

    // Elimina la solicitud con mayor prioridad

    public void eliminarSolicitudPrioritaria() {
        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarSolicitudPrioritaria en ColaPrioridadSolicitudes. Correcto.");
        cola.poll();
    }

    // Elimina una solicitud específica

    public void eliminarSolicitud(SolicitudAyuda solicitud) {
        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarSolicitud en ColaPrioridadSolicitudes. Correcto.");
        cola.remove(solicitud);
    }

    // Verifica si la cola está vacía

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    // Retorna todas las solicitudes

    public PriorityQueue<SolicitudAyuda> obtenerTodasLasSolicitudes() {
        Utilidades.getInstance().escribirLog(Level.INFO, "Método obtenerTodasLasSolicitudes en ColaPrioridadSolicitudes. Correcto.");
        return new PriorityQueue<>(cola); // Evita exponer la lista original
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