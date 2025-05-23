package proyectofinal.Modelo;

import java.io.Serializable;

public class NodoContenido<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Contenido contenido;
    private NodoContenido izquierdo;
    private NodoContenido derecho;

    public NodoContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    //getters and setters
    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }

    public NodoContenido getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoContenido izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoContenido getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoContenido derecho) {
        this.derecho = derecho;
    }
}
