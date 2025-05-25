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
            redirigirVista("inicio.fxml");
        } else if (mod != null) {
            redirigirVista("panelModerador.fxml");
        } else {
            mostrarAlerta("Error de inicio de sesión", "Nombre de usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void handleRegister() {
        redirigirVista("opcionRegistro.fxml");
    }

    @FXML
    public void handleCargarDatos(ActionEvent actionEvent) {
        try {
            redSocial = new RedSocial("RedSocialAprendizajePrueba");

            Estudiante est1 = new Estudiante("Sofia", "Buitrago", "333");
            Estudiante est2 = new Estudiante("Luis", "Martinez", "123");
            Estudiante est3 = new Estudiante("Valeria", "Torres", "456");

            Moderador mod = new Moderador("Celeste", "111");

            redSocial.registrarEstudiante(est1);
            redSocial.registrarEstudiante(est2);
            redSocial.registrarEstudiante(est3);
            redSocial.registrarModerador(mod);

            // Crear el grafo con conexiones manuales (datos quemados)
            GrafoAfinidad grafo = new GrafoAfinidad();
            grafo.agregarEstudiante(est1);
            grafo.agregarEstudiante(est2);
            grafo.agregarEstudiante(est3);
            grafo.conectar(est1, est2);
            grafo.conectar(est2, est3);

            redSocial.setGrafo(grafo);

            Persistencia.guardarRedSocial(redSocial);

            System.out.println("== Estudiantes cargados ==");
            redSocial.getEstudiantes().forEach((id, estudiante) -> {
                System.out.println("Nombre: " + estudiante.getNombre() + ", Contraseña: " + estudiante.getContrasena());
            });
            System.out.println("== Moderadores cargados ==");
            redSocial.getModeradores().forEach((id, moderador) -> {
                System.out.println("Nombre: " + moderador.getNombre() + ", Contraseña: " + moderador.getContrasena());
            });
            mostrarAlerta("Datos Cargados", "Se cargó una nueva red social con datos de prueba.");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al Cargar Datos", "Ocurrió un error al crear o guardar los datos.\n" + e.getMessage());
        }
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
}