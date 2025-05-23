package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.*;
import proyectofinal.Modelo.*;

import java.io.IOException;

public class LoginController {
    private RedSocial redSocial;

    @FXML
    private TextField tfnombreUsuario;

    @FXML
    private PasswordField pfcontrasena;

    @FXML
    private void handleLogin() {
        String nombre = tfnombreUsuario.getText();
        String contrasena = pfcontrasena.getText();

        Estudiante est = redSocial.autenticarEstudiante(nombre, contrasena);
        Moderador mod = redSocial.autenticarModerador(nombre, contrasena);

        if (est != null) {
            redirigirVista("inicio.fxml");
        } else if (mod != null) {
            redirigirVista("panelModerador.fxml");
        } else {
            mostrarAlerta(AlertType.ERROR, "Error de inicio de sesión", "Nombre de usuario o contraseña incorrectos.");
        }
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    @FXML
    private void handleRegister() {
        redirigirVista("opcionRegistro.fxml");
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void redirigirVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxml));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof OpcionRegistroController) {
                ((OpcionRegistroController) controller).setRedSocial(redSocial);
            }

            Stage stage = (Stage) tfnombreUsuario.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta(AlertType.ERROR, "Error", "No se pudo cargar la vista: " + fxml);
        }
    }
}