package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import proyectofinal.Modelo.Estudiante;
import proyectofinal.Modelo.RedSocial;
import proyectofinal.Utilidades.Persistencia;

import java.util.Map;

public class RegistroEstudianteController {

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellido;

    @FXML
    private PasswordField pfContrasena;

    private RedSocial redSocial;

    private void limpiarCampos() {
        tfNombre.clear();
        tfApellido.clear();
        pfContrasena.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void handleRegistrarEstudiante(ActionEvent actionEvent) {
        String nombre = tfNombre.getText();
        String apellido = tfApellido.getText();
        String contrasena = pfContrasena.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Por favor, complete todos los campos.");
            return;
        }

        Estudiante estudiante = new Estudiante(nombre, apellido, contrasena);

        redSocial.registrarEstudiante(estudiante);
        Persistencia.guardarRedSocial(redSocial);

        mostrarAlerta("Éxito", "Estudiante registrado correctamente.");
        limpiarCampos();
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}