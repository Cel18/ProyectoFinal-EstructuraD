package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import proyectofinal.Modelo.Moderador;
import proyectofinal.Modelo.RedSocial;
import proyectofinal.Utilidades.Persistencia;

public class RegistroModeradorController {

    @FXML
    private TextField tfNombre;

    @FXML
    private PasswordField pfContrasena;

    private RedSocial redSocial;

    private void limpiarCampos() {
        tfNombre.clear();
        pfContrasena.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void handleRegistrarModerador(ActionEvent actionEvent) {
        String nombre = tfNombre.getText();
        String contrasena = pfContrasena.getText();

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        Moderador mod = new Moderador(nombre, contrasena);
        redSocial.registrarModerador(mod);

        Persistencia.guardarRedSocial(redSocial);

        mostrarAlerta("Éxito", "Moderador registrado correctamente.");
        limpiarCampos();
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}