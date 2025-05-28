package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.*;
import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

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
            redSocial.setEstudianteActivo(est);
            ListaEnlazada<Contenido> contenidosCargados = Persistencia.cargarContenido(est.getNombreCompleto());
            est.setContenidosPublicados(contenidosCargados);
            redirigirVista("inicio.fxml");
        } else if (mod != null) {
            redSocial.setModeradorActivo(mod);
            redirigirVista("panelModerador.fxml");
        } else {
            mostrarAlerta("Error de inicio de sesión", "Nombre de usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void handleRegister() {
        redirigirVista("opcionRegistro.fxml");
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void redirigirVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxml));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof OpcionRegistroController) {
                ((OpcionRegistroController) controller).setRedSocial(redSocial);
            } else if (controller instanceof InicioController) {
                ((InicioController) controller).setRedSocial(redSocial);
            } else if (controller instanceof PanelModeradorController) {
                ((PanelModeradorController) controller).setRedSocial(redSocial);
            }

            Stage stage = (Stage) tfnombreUsuario.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista: " + fxml);
        }
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    @FXML
    public void handleCargarDatos(ActionEvent actionEvent) {
        try {
            this.redSocial = redSocial.cargarDatosPrueba();

            for (Estudiante est : redSocial.getEstudiantes().values()) {
                ListaEnlazada<Contenido> contenidosCargados = Persistencia.cargarContenido(est.getNombreCompleto());
                est.setContenidosPublicados(contenidosCargados);
            }
            mostrarAlerta("Datos Cargados", "Se cargó una nueva red social con datos de prueba.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al Cargar Datos", "Ocurrió un error al crear o guardar los datos.\n" + e.getMessage());
        }
    }
}