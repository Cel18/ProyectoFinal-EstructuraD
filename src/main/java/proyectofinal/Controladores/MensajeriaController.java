package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Utilidades;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;

public class MensajeriaController {

    @FXML
    private ListView<String> listaContactos;

    @FXML
    private TextArea areaMensajes;

    @FXML
    private TextField campoMensaje;

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private Estudiante estudiante;
    private Estudiante otroEstudiante;
    private ArrayList<Mensaje> historialMensajes = new ArrayList<>();

    public void iniciarCliente(String direccionServidor, int puerto, Estudiante estudiante) {
        this.estudiante = estudiante;

        try {
            socket = new Socket(direccionServidor, puerto);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método iniciarCliente en MensajeriaController. Incorrecto, excepción.");
            e.printStackTrace();
        }
        new Thread(this::recibirMensajes).start();
    }

    private void recibirMensajes() {
        try {
            String mensaje;
            while ((mensaje = reader.readLine()) != null) {
                historialMensajes.add(new Mensaje(otroEstudiante, mensaje)); //Ya luego soluciono esto
                cargarConversacion();
            }
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método recibirMensaje en MensajeriaController. Incorrecto, excepción.");
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        listaContactos.getItems().addAll("Estudiante1", "Estudiante2", "Estudiante3");
        listaContactos.getSelectionModel().selectFirst();
        cargarConversacion();
    }


    private void cargarConversacion() {
        String contacto = listaContactos.getSelectionModel().getSelectedItem();
        if (contacto != null) {
            areaMensajes.setText("Conversación con " + contacto + ":\n");
            for (Mensaje mensaje : historialMensajes) {
                areaMensajes.appendText(mensaje.toString() + "\n");
            }
        }
    }

    @FXML
    private void handleEnviarMensaje() {
        String contenido = campoMensaje.getText();
        if (!contenido.trim().isEmpty()) {
            Mensaje mensaje = new Mensaje(estudiante, contenido);
            writer.println(mensaje.toString());
            historialMensajes.add(mensaje);
            campoMensaje.clear();
            cargarConversacion();
        }
    }

}