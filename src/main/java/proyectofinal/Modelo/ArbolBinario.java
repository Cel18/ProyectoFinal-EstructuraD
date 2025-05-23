package proyectofinal.Modelo;

public class ArbolBinario <T extends Contenido>{

    private int peso;
    private NodoContenido<T> raiz;

    public ArbolBinario() {
        this.peso = 0;
        this.raiz = null;
    }
}
