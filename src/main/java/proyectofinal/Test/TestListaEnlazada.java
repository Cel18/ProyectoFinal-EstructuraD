package proyectofinal.Test;

import proyectofinal.Modelo.*;

public class TestListaEnlazada {

    public static void main(String[] args) {
        ListaEnlazada<Contenido> listaEnlazada = new ListaEnlazada<>();

        NodoContenido<Contenido> c1 = new NodoContenido<>(new Contenido("Primera Ley de la Relatividad de las Fresas",new Estudiante("Celeste", "Buitrago", "1"), TipoContenido.FISICA));
        NodoContenido<Contenido> c2 = new NodoContenido<>(new Contenido("La Odiosa",new Estudiante("Sofia", "Cebolla", "1"), TipoContenido.LECTOESCRITURA));
        NodoContenido<Contenido> c3 = new NodoContenido<>(new Contenido("Zafrán, salsa de tomate",new Estudiante("Anonimo", "Sopa", "1"), TipoContenido.BIOLOGIA));
        NodoContenido<Contenido> c4 = new NodoContenido<>(new Contenido("Wooloo, la oveja asesina",new Estudiante("Juanse", "pr", "1"), TipoContenido.LECTOESCRITURA));

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
