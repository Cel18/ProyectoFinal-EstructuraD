package proyectofinal.Modelo;

import proyectofinal.Utilidades.Utilidades;

import java.util.logging.Level;

public class ListaEnlazada <T> {

    private NodoContenido<T> inicial;
    private int tamanio;

    public ListaEnlazada() {
        this.inicial = null;
        this.tamanio = 0;
    }

    //Método para añadir un nodo a la lista

    public void insertarNodoInicio(T contenido) {
        NodoContenido<T> nuevoNodo = new NodoContenido<>(contenido);

        if (inicial != null) {
            nuevoNodo.setDerecho(inicial);
            inicial.setIzquierdo(nuevoNodo);
        }

        inicial = nuevoNodo;
        tamanio++;
        Utilidades.getInstance().escribirLog(Level.INFO, "Método insertarNodoInicio en ListaEnlazada. Correcto.");
    }

    //Método para eliminar un nodo en específico

    public void eliminarNodo(T contenido) {
        if (inicial == null) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarNodo en ListaEnlazada. Incorrecto, no hay nodos en la lista.");
            return;
        }

        //Si el nodo a eliminar es el primero
        if (inicial.getContenido().toString().equals(contenido.toString())) {
            inicial = inicial.getDerecho();
            if (inicial != null) {
                inicial.setIzquierdo(null);
            }
            tamanio--;
            Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarNodo en ListaEnlazada. Correcto.");
            return;
        }

        NodoContenido<T> nodoActual = inicial;
        while (nodoActual != null && !nodoActual.getContenido().toString().equals(contenido.toString())) {
            nodoActual = nodoActual.getDerecho();
        }

        if (nodoActual != null) {
            if (nodoActual.getIzquierdo() != null) {
                nodoActual.getIzquierdo().setDerecho(nodoActual.getDerecho());
            }

            if (nodoActual.getDerecho() != null) {
                nodoActual.getDerecho().setIzquierdo(nodoActual.getIzquierdo());
            }
            tamanio--;
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarNodo en ListaEnlazada. Correcto.");
    }

    //Método para mostrar la lista enlazada

    public String mostrarLista() {
        NodoContenido<T> nodoRecorrer = inicial;
        String cadena = "";
        while (nodoRecorrer != null) {
            cadena+= nodoRecorrer.getContenido().toString() + " <-> ";
            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO,"Método mostrarLista en ListaEnlazada. Correcto.");
        return cadena;
    }

    //Método para buscar un nodo

    public boolean buscarNodo(T contenido) {
        NodoContenido<T> nodoRecorrer = inicial;

        while (nodoRecorrer != null) {
            if (nodoRecorrer.getContenido().toString().equals(contenido.toString())) {
                Utilidades.getInstance().escribirLog(Level.INFO,"Método buscarNodo en ListaEnlazada. Correcto.");
                return true;
            }
            nodoRecorrer = nodoRecorrer.getDerecho();
        }

        Utilidades.getInstance().escribirLog(Level.INFO,"Método buscarNodo en ListaEnlazada. No encontró el nodo.");
        return false;
    }


    //Getters y Setters
    public NodoContenido<T> getInicial() {
        return inicial;
    }

    public void setContenido(NodoContenido<T> inicial) {
        this.inicial = inicial;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }
}