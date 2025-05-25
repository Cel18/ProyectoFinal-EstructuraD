package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;
import proyectofinal.Modelo.RedSocial;

import java.io.IOException;

public class InicioController {
    private RedSocial redSocial;

    private void redirigirVista(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxml));
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof PanelEstudianteController) {
                ((PanelEstudianteController) controller).setRedSocial(redSocial);
            } else if (controller instanceof PerfilEstudianteController) {
                ((PerfilEstudianteController) controller).setRedSocial(redSocial);
                ((PerfilEstudianteController) controller).cargarPerfil();
                ((PerfilEstudianteController) controller).setVistaAnterior("/proyectofinal/inicio.fxml");
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

    private Stage obtenerStage() {
        return (Stage) Window.getWindows().stream()
                .filter(Window::isShowing)
                .findFirst()
                .orElse(null);
    }

    @FXML
    private void handleExplorarContenidos(ActionEvent event) {
        redirigirVista("panelEstudiante.fxml");
    }

    @FXML
    private void handleAccederPerfil(ActionEvent event) {
        redirigirVista("perfilEstudiante.fxml");
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}