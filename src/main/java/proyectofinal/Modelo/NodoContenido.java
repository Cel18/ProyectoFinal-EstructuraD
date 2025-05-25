package proyectofinal.Modelo;

import java.io.Serial;
import java.io.Serializable;

public class NodoContenido <T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private T contenido;
    private NodoContenido<T> izquierdo;
    private NodoContenido<T> derecho;

    public NodoContenido(T contenido) {
        this.contenido = contenido;
        this.izquierdo = null;
        this.derecho = null;
    }

    //Método para el nodo del Árbol binario para saber si es una hoja

    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    //Getters and setters
    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    public NodoContenido<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoContenido<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoContenido<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoContenido<T> derecho) {
        this.derecho = derecho;
    }
}
