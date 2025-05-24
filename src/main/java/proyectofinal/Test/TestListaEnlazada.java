package proyectofinal.Test;

import proyectofinal.Modelo.*;

public class TestListaEnlazada {

    public static void main(String[] args) {
        ListaEnlazada<Contenido> listaEnlazada = new ListaEnlazada<>();

        NodoContenido<Contenido> c1 = new NodoContenido<>(new Contenido("Primera Ley de la Relatividad de las Fresas","Celeste", TipoContenido.FISICA, "001"));
        NodoContenido<Contenido> c2 = new NodoContenido<>(new Contenido("La Odiosa","Sofía", TipoContenido.LECTOESCRITURA, "002"));
        NodoContenido<Contenido> c3 = new NodoContenido<>(new Contenido("Zafrán, salsa de tomate","Custom Culinary", TipoContenido.BIOLOGIA, "003"));
        NodoContenido<Contenido> c4 = new NodoContenido<>(new Contenido("Wooloo, la oveja asesina","JuanSe", TipoContenido.LECTOESCRITURA, "004"));

        listaEnlazada.insertarNodoInicio(c1.getContenido());
        listaEnlazada.insertarNodoInicio(c2.getContenido());
        listaEnlazada.insertarNodoInicio(c3.getContenido());
        listaEnlazada.insertarNodoInicio(c4.getContenido());

        listaEnlazada.eliminarNodo(c3.getContenido());

        System.out.println(listaEnlazada.buscarNodo(c3.getContenido()));
        System.out.println(listaEnlazada.buscarNodo(c4.getContenido()));
        System.out.println(listaEnlazada.mostrarLista());

    }
}
