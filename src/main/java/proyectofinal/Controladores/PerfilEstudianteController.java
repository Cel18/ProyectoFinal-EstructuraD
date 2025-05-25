package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import proyectofinal.Modelo.RedSocial;

import java.io.IOException;

public class PerfilEstudianteController {
    private RedSocial redSocial;
    private String vistaAnterior;

    @FXML
    private Label lblId;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblApellido;
    @FXML
    private Label lblContrasena;
    @FXML
    private Label lblContenidos;
    @FXML
    private Label lblPromedio;
    @FXML
    private ListView<String> listaConexiones;

    public void handleCerrarSesion(ActionEvent actionEvent) {
        mostrarAlerta("Cerrar Sesión", "Has cerrado sesión exitosamente.");
        redirigirVista("login.fxml");
    }

    private void redirigirVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxml));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof LoginController) {
                ((LoginController) controller).setRedSocial(redSocial);
            }

            Stage stage = obtenerStage();
            if (stage != null) {
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
                stage.show();
            } else {
                mostrarAlerta("Error", "No se encontró la siguiente ventana.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista: " + fxml);
        }
    }

    public void cargarPerfil(){
        if (redSocial != null) {
            var estudiante = redSocial.getEstudianteActivo();

            if (estudiante != null) {
                lblId.setText(String.valueOf(estudiante.getId()));
                lblNombre.setText(estudiante.getNombre());
                lblApellido.setText(estudiante.getApellido());
                lblContrasena.setText(estudiante.getContrasena());
                lblContenidos.setText(String.valueOf(estudiante.getContenidosPublicados().getTamanio()));
                lblPromedio.setText(String.format("%.2f", estudiante.getPromedioValoraciones()));

                listaConexiones.getItems().clear();
                for (var conexion : estudiante.getConexiones()) {
                    listaConexiones.getItems().add(conexion.getNombreCompleto()); // O toString()
                }
            } else {
                mostrarAlerta("Error", "No hay estudiante activo.");
            }
        } else {
            mostrarAlerta("Error", "No se pudo cargar el perfil del estudiante.");
        }
    }

    private Stage obtenerStage() {
        return (Stage) Window.getWindows().stream()
                .filter(Window::isShowing)
                .findFirst()
                .orElse(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    public void setVistaAnterior(String vistaAnterior) {this.vistaAnterior = vistaAnterior;}

    @FXML
    private void handleVolver(MouseEvent event) {
        if (vistaAnterior == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vistaAnterior));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof PanelEstudianteController) {
                ((PanelEstudianteController) controller).setRedSocial(redSocial);
            } else if (controller instanceof InicioController) {
                ((InicioController) controller).setRedSocial(redSocial);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene nuevaEscena = new Scene(root);
            stage.setScene(nuevaEscena);
            stage.sizeToScene();
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}