package proyectofinal.Modelo;

import proyectofinal.Utilidades.Utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

public class Chat {
    private static final int PUERTO = 12345;
    private static final CopyOnWriteArrayList<Socket> estudiantes = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            Utilidades.getInstance().escribirLog(Level.INFO, "Método main en Chat. Correcto, creación del serverSocket.");

            while (true) {
                Socket estudianteSocket = serverSocket.accept();
                estudiantes.add(estudianteSocket);
                new Thread(new ClienteHandler(estudianteSocket));
            }
        } catch (IOException e) {
            Utilidades.getInstance().escribirLog(Level.WARNING, "Método main en Chat. Incorrecto, excepción.");
            e.printStackTrace();
        }
    }

    static class ClienteHandler implements Runnable {
        private final Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String mensaje;
                while ((mensaje = reader.readLine()) != null ) {
                    manejarMensaje(mensaje);
                }
            } catch (IOException e) {
                Utilidades.getInstance().escribirLog(Level.WARNING, "Método run en Chat. Incorrecto, excepción.");
                e.printStackTrace();
            } finally {
                estudiantes.remove(socket);
            }
        }

        private void manejarMensaje(String mensaje) {
            for (Socket estudiante : estudiantes) {
                try {
                    PrintWriter writer = new PrintWriter(estudiante.getOutputStream(), true);
                    writer.println(mensaje);
                } catch (IOException e) {
                    Utilidades.getInstance().escribirLog(Level.WARNING, "Método manejarMensaje en Chat. Incorrecto, excepción.");
                    e.printStackTrace();
                }
            }
        }
    }
}