package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validación básica (puedes reemplazar con lógica real)
        if (username.equals("admin") && password.equals("1234")) {
            mostrarAlerta(AlertType.INFORMATION, "Inicio de sesión exitoso", "Bienvenido, " + username + "!");
        } else {
            mostrarAlerta(AlertType.ERROR, "Error de inicio de sesión", "Nombre de usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void handleRegister() {
        mostrarAlerta(AlertType.INFORMATION, "Registro", "Funcionalidad de registro aún no implementada.");
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}