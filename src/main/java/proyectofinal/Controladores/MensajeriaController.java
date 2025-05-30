package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyectofinal.Modelo.RedSocial;

public class MensajeriaController {
    private RedSocial redSocial;

    @FXML
    private ListView<String> listaContactos;

    @FXML
    private TextArea areaMensajes;

    @FXML
    private TextField campoMensaje;

    @FXML
    public void initialize() {
        // Ejemplo de contactos
        listaContactos.getItems().addAll("Estudiante1", "Estudiante2", "Estudiante3", "Estudiante4", "Estudiante5", "Estudiante6");

        // Simulación de selección inicial
        listaContactos.getSelectionModel().selectFirst();
        cargarConversacion();

        listaContactos.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            cargarConversacion();
        });
    }

    private void cargarConversacion() {
        String contacto = listaContactos.getSelectionModel().getSelectedItem();
        if (contacto != null) {
            // Simular mensajes (en app real: cargar de BD)
            areaMensajes.setText("Conversación con " + contacto + ":\nHola, ¿cómo estás?");
        }
    }

    @FXML
    private void handleEnviarMensaje() {
        String mensaje = campoMensaje.getText();
        if (!mensaje.trim().isEmpty()) {
            areaMensajes.appendText("\nYo: " + mensaje);
            campoMensaje.clear();
        }
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}