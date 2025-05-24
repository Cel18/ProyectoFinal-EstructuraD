package proyectofinal.Modelo;

import jdk.jshell.execution.Util;
import proyectofinal.Utilidades.Utilidades;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;

public class ArbolBinario <T>{

    private int peso;
    private NodoContenido<T> raiz;

    //Constructor de ÁrbolBinario

    public ArbolBinario() {
        this.raiz = null;
        this.peso = 0;
    }

    //Constructor del subárbol de ÁrbolBinario

    private ArbolBinario(NodoContenido<T> raiz) {
        this.raiz = null;
        this.peso = obtenerPesoSubarbol(raiz, peso);
        this.raiz = raiz;
    }

    /*
    * Métodos para agregar, eliminar y buscar nodo en el árbol
    */
    //Método para agregar un nodo al árbol

    public void agregarNodo(T contenido) {
        NodoContenido<T> nuevoNodo = new NodoContenido<>(contenido);

        if (esVacio()) {
            raiz = nuevoNodo;
            peso++;
            Utilidades.getInstance().escribirLog(Level.INFO,"Método agregarNodo en ArbolBinario. Correcto.");
            return;
        }

        if (!existe(contenido)) {
            agregarNodoRecursivo(nuevoNodo, raiz);
        }
    }

    //Método para eliminar un nodo del árbol

    public void eliminarNodo(T contenido) {
        if (existe(contenido)) {
            if (raiz.getContenido().toString().equals(contenido.toString())) {
                eliminarPrimero();
                return;
            }
            eliminarNodoRecursivo(contenido, raiz); //Falta el compareTo
        }
    }

    /*
     * Casos de eliminación.
     * 1. No tiene hijos, es una hoja.
     * 2. Tiene 1 hijo
     * 3. Tiene 2 hijos
     */

    public void eliminarPrimero() {

            //Caso 3. Tiene 2 Hijos.
        if (raiz.getIzquierdo() != null && raiz.getDerecho() != null) {
            NodoContenido<T> nodoMayorIzquierda = obtenerNodoMayor(raiz.getIzquierdo());
            eliminarNodoRecursivo(nodoMayorIzquierda.getContenido(), raiz);
            raiz.setContenido(nodoMayorIzquierda.getContenido());

            //Caso 2. Tiene 1 hijo.
        } else if (raiz.getIzquierdo() != null) {
            T elementoIzquierda = raiz.getIzquierdo().getContenido();
            eliminarNodoRecursivo(elementoIzquierda, raiz);
            raiz.setContenido(elementoIzquierda);

        } else if (raiz.getDerecho() != null) {
            T elementoDerecha = raiz.getDerecho().getContenido();
            eliminarNodoRecursivo(elementoDerecha, raiz);
            raiz.setContenido(elementoDerecha);

            //Caso 1. No tiene hijos.
        } else {
            borrarArbol();
        }
    }

    //Método para buscar un nodo

    public NodoContenido<T> buscarNodo(T contenido, NodoContenido<T> actual) {
        if (actual == null || !crearSubarbol(actual).existe(contenido)) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarNodo en ArbolBinario. Incorrecto, No existe el nodo.");
            return null;
        }

        if (actual.getContenido().toString().equals(contenido.toString())) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarNodo en ArbolBinario. Correcto.");
            return actual;
        }

        if (actual.getContenido().compareTo(contenido) < 0) {
            buscarNodo(contenido, actual.getIzquierdo());

        } else if (actual.getContenido().compareTo(contenido) > 0) {
            buscarNodo(contenido, actual.getDerecho());

        }

        Utilidades.getInstance().escribirLog(Level.INFO, "Método buscarNodo en ArbolBinario. Incorrecto, no existe el nodo.");
        return null;
    }

    //Método para obtener la altura del árbol

    public int obtenerAlturaArbol() {
        int altura = 0;
        if (!esVacio()) {
            altura = obtenerAlturaArbolRecursivo(raiz, altura);
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método obtenerAlturaArbol en ArbolBinario. Correcto.");
        return altura;
    }

    //Método para contar sus hojas

    public int contarHojasArbol() {
        int numeroHojas = 0;
        if (!esVacio()) {
            numeroHojas = contarHojasArbolRecursivo(raiz, numeroHojas);
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método contarHojasArbol en ArbolBinario. Correcto.");
        return numeroHojas;
    }

    public String obtenerAmplitudArbol() {
        String amplitud = "";
        if (!esVacio()) {
            StringBuilder sb = new StringBuilder();
            Queue<NodoContenido<T>> colaNodos = new LinkedList<>();
            colaNodos.add(raiz);

            while (!colaNodos.isEmpty()) {
                NodoContenido<T> actual = colaNodos.poll();
                sb.append(actual.getContenido());

                if (actual.getIzquierdo() != null) {
                    colaNodos.add(actual.getIzquierdo());
                }

                if (actual.getDerecho() != null) {
                    colaNodos.add(actual.getDerecho());
                }

                if (!colaNodos.isEmpty()) {
                    sb.append(" - ");
                }
            }

            amplitud = "[" + sb + "]";
        } else {
            amplitud = "[ ]";
        }

        return amplitud;
    }

    //Método para confirmar si existe un nodo

    public boolean existe(T contenido) {
        boolean existe = false;
        if (contenido != null) {
            existe = comprobarExistenciaRecursivo(contenido, raiz, false);
        }
        Utilidades.getInstance().escribirLog(Level.INFO,"");
        return existe;
    }

    //Método para confirmar si el árbol está vacío o no

    public boolean esVacio() {
        return raiz == null;
    }

    /*
    * Métodos para recorrer el árbol, para hallar desbalances y crear subárboles
    */

    //Métodos para recorrer el árbol en preorden, inorden y postorden

    public String recorrerPreorden() {
        String cadena = "";
        if (!esVacio()) {
            StringBuilder stringBuilder = new StringBuilder();
            cadena = recorrerPreordenRecursivo(raiz, cadena);
            stringBuilder.append(cadena).delete(cadena.length() - 3, cadena.length());
            cadena = "[" + stringBuilder + "]";
        } else {
            cadena = "[ ]";
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método recorrerPreorden en ArbolBinario. Correcto.");
        return  cadena;
    }

    public String recorrerInorden() {
        String cadena = "";
        if (!esVacio()) {
            StringBuilder sb = new StringBuilder();
            cadena = recorrerInordenRecursivo(raiz, cadena);
            sb.append(cadena).delete(cadena.length() - 3, cadena.length());
            cadena = "[" + sb + "]";
        } else {
            cadena = "[ ]";
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método recorrerInorden en ArbolBinario. Correcto.");
        return cadena;
    }

    public String recorrerPostorden() {
        String cadena = "";
        if (!esVacio()) {
            StringBuilder sb = new StringBuilder();
            cadena = recorrerPostordenRecursivo(raiz, cadena);
            sb.append(cadena).delete(cadena.length() - 3, cadena.length());
            cadena = "[" + sb + "]";
        } else {
            cadena = "[ ]";
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método recorrerPostorden en ArbolBinario. Correcto.");
        return cadena;
    }

    //Método para obtener la rama mayor y menor

    public T obtenerMayor() {
        NodoContenido<T> actual = raiz;
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }

        return actual.getContenido();
    }

    public T obtenerMenor() {
        NodoContenido<T> actual = raiz;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual.getContenido();
    }

    //Método para borrar el árbol

    public void borrarArbol() {
        raiz = null;
        peso = 0;
    }


    //Métodos para obtener el nodo mayor y menor del árbol

    public NodoContenido<T> obtenerNodoMayor(NodoContenido<T> actual) {
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método obtenerNodoMayor en ArbolBinario. Correcto.");
        return actual;
    }

    public NodoContenido<T> obtenerNodoMenor(NodoContenido<T> actual) {
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        Utilidades.getInstance().escribirLog(Level.INFO, "Método obtenerNodoMenor en ArbolBinario. Correcto.");
        return actual;
    }

    //Método para crear un subárbol de ÁrbolBinario

    public ArbolBinario<T> crearSubarbol(NodoContenido<T> raiz) {
        return new ArbolBinario<>();
    }

    //Método para obtener el peso del subárbol
    public int obtenerPesoSubarbol(NodoContenido<T> raiz, int peso) {
        if (raiz == null) {
            return peso;
        }

        if (raiz.getIzquierdo() == null && raiz.getDerecho() == null) {
            return 1;
        }

        int pesoIzquierdo = obtenerPesoSubarbol(raiz.getIzquierdo(), peso);
        int pesoDerecho = obtenerPesoSubarbol(raiz.getDerecho(), peso);

        peso += 1 + pesoIzquierdo + pesoDerecho;

        Utilidades.getInstance().escribirLog(Level.INFO, "Método obtenerPesoSubarbol en ArbolBinario. Correcto.");
        return peso;
    }

    /*
    * Métodos recursivos auxiliares para agregar, eliminar y buscar en el árbol
    */

    //Métodos recursivos

    public void agregarNodoRecursivo(NodoContenido<T> nuevoNodo, NodoContenido<T> raiz) {
        if (raiz == null) {
            return;
        }

        //Falta terminar el árbol binario
    }

    public void eliminarNodoRecursivo(T contenido, NodoContenido<T> actual) {
        if (actual == null) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método eliminarNodoRecursivo en ArbolBinario. Correcto.");
            return;
        }

        //Caso Base
        if (contenido.compareTo(actual.getContenido()) < 0) {
            if (actual.getIzquierdo().getContenido().toString().equals(contenido.toString())) {

                NodoContenido<T> nodoAEliminar = actual.getIzquierdo();
                    //Caso 3. Tiene 2 hijos.
                if (nodoAEliminar.getIzquierdo() != null && nodoAEliminar.getDerecho() != null) {
                    NodoContenido<T> nodoMayorIzquierda = obtenerNodoMayor(nodoAEliminar.getIzquierdo());
                    eliminarNodoRecursivo(nodoMayorIzquierda.getContenido(), nodoAEliminar);
                    nodoAEliminar.setContenido(nodoMayorIzquierda.getContenido());

                    //Caso 2. Tiene 1 hijo.
                } else if (nodoAEliminar.getIzquierdo() != null) {
                    actual.setIzquierdo(nodoAEliminar.getIzquierdo());

                } else if (nodoAEliminar.getDerecho() != null) {
                    actual.setIzquierdo(nodoAEliminar.getDerecho());

                    //Caso 1. No tiene hijos.
                } else {
                    actual.setIzquierdo(null);
                }
                return;
            }

            eliminarNodoRecursivo(contenido, actual.getIzquierdo());

        } else {
            if (actual.getDerecho().getContenido().toString().equals(contenido.toString())) {
                NodoContenido<T> nodoAEliminar = actual.getDerecho();
                    //Caso 3. Tiene dos hijos.
                if (nodoAEliminar.getIzquierdo() != null && nodoAEliminar.getDerecho() != null) {
                    NodoContenido<T> nodoMayorIzquierda = obtenerNodoMayor(nodoAEliminar.getIzquierdo());
                    eliminarNodoRecursivo(nodoMayorIzquierda.getContenido(), nodoAEliminar);
                    nodoAEliminar.setContenido(nodoMayorIzquierda.getContenido());

                    //Caso 2. Tiene 1 hijo.
                } else if (nodoAEliminar.getIzquierdo() != null) {
                    actual.setDerecho(nodoAEliminar.getIzquierdo());

                } else if (nodoAEliminar.getDerecho() != null) {
                    actual.setDerecho(nodoAEliminar.getDerecho());

                    //Caso 1. No tiene hijos.
                } else {
                    actual.setDerecho(null);
                }
                return;
            }

            eliminarNodoRecursivo(contenido, actual.getDerecho());
        }
    }

    public boolean comprobarExistenciaRecursivo(T contenido, NodoContenido<T> raiz, boolean resultado) {
        if (raiz == null) {
            return false;
        }

        if (raiz.getContenido().toString().equals(contenido.toString())) {
            return true;
        }

        boolean resultadoIzquierda = comprobarExistenciaRecursivo(contenido, raiz.getIzquierdo(), resultado);
        boolean resultadoDerecha = comprobarExistenciaRecursivo(contenido, raiz.getDerecho(), resultado);

        Utilidades.getInstance().escribirLog(Level.INFO, "Método comprobarExistenciaRecursivo en ArbolBinario. Correcto.");
        return resultadoIzquierda || resultadoDerecha;
    }

    //Métodos recursivos para obtener la altura y las hojas del árbol

    private int obtenerAlturaArbolRecursivo(NodoContenido<T> raiz, int altura) {
        if (raiz == null) {
            return altura;
        }

        if (raiz.esHoja()) {
            return altura + 1;
        }

        int alturaIzquierda = obtenerAlturaArbolRecursivo(raiz.getIzquierdo(), altura + 1);
        int alturaDerecha = obtenerAlturaArbolRecursivo(raiz.getDerecho(), altura + 1);

        Utilidades.getInstance().escribirLog(Level.INFO, "Método obtenerAlturaRecursivo en ArbolBinario. Correcto.");
        return Math.max(alturaIzquierda, alturaDerecha);
    }

    private int contarHojasArbolRecursivo(NodoContenido<T> raiz, int numeroHojas) {
        if (raiz == null) {
            return numeroHojas;
        }

        if (raiz.esHoja()) {
            return numeroHojas + 1;
        }

        int alturaIzquierda = contarHojasArbolRecursivo(raiz.getIzquierdo(), numeroHojas);
        int alturaDerecha = contarHojasArbolRecursivo(raiz.getDerecho(), numeroHojas);

        Utilidades.getInstance().escribirLog(Level.INFO, "Método contarHojasRecursivo en ArbolBinario. Correcto.");
        return alturaIzquierda + alturaDerecha;
    }

    //Métodos recursivos para recorrer el árbol recursivos de Preorden, Inorden y Postorden

    public String recorrerPreordenRecursivo(NodoContenido<T> raiz, String cadena) {
        if (raiz == null) {
            return "";
        }

        if (raiz.esHoja()) {
            return raiz.getContenido().toString() + " - ";
        }

        String subarbolIzquierdo = recorrerPreordenRecursivo(raiz.getIzquierdo(), cadena);
        String subarbolDerecho = recorrerPreordenRecursivo(raiz.getDerecho(), cadena);

        cadena += raiz.getContenido() + " - " + subarbolIzquierdo + subarbolDerecho;
        Utilidades.getInstance().escribirLog(Level.INFO, "Método recorrerPreordenRecursivo en ArbolBinario. Correcto.");
        return cadena;
    }

    public String recorrerInordenRecursivo(NodoContenido<T> raiz, String cadena) {
        if (raiz == null) {
            return "";
        }

        if (raiz.esHoja()) {
            return raiz.getContenido().toString() + " - ";
        }

        String subarbolIzquierdo = recorrerInordenRecursivo(raiz.getIzquierdo(), cadena);
        String subarbolDerecho = recorrerInordenRecursivo(raiz.getDerecho(), cadena);

        cadena += subarbolIzquierdo + raiz.getContenido() + " - " + subarbolDerecho;
        Utilidades.getInstance().escribirLog(Level.INFO, "Método recorrerInordenRecursivo en ArbolBinario. Correcto.");
        return cadena;
    }

    public String recorrerPostordenRecursivo(NodoContenido<T> raiz, String cadena) {
        if (raiz == null) {
            return "";
        }

        if (raiz.esHoja()) {
            return raiz.getContenido().toString() + " - ";
        }

        String subarbolIzquierdo = recorrerPostordenRecursivo(raiz.getIzquierdo(), cadena);
        String subarbolDerecho = recorrerPostordenRecursivo(raiz.getDerecho(), cadena);

        cadena += subarbolIzquierdo + subarbolDerecho + raiz.getContenido() + " - ";
        Utilidades.getInstance().escribirLog(Level.INFO, "Método recorrerPostordenRecursivo en ArbolBinario. Correcto.");
        return cadena;
    }

    /*
    * Métodos de existencia de un nodo en el árbol
    */

    //Getters y Setters

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public NodoContenido<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoContenido<T> raiz) {
        this.raiz = raiz;
    }
}