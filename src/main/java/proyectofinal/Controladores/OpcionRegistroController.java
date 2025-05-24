package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyectofinal.Modelo.RedSocial;

import java.io.IOException;

public class OpcionRegistroController {
    private RedSocial redSocial;

    private void abrirVentana(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof RegistroEstudianteController) {
                ((RegistroEstudianteController) controller).setRedSocial(redSocial);
            } else if (controller instanceof RegistroModeradorController) {
                ((RegistroModeradorController) controller).setRedSocial(redSocial);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRegistroEstudiante(ActionEvent actionEvent) {
        abrirVentana("registroEstudiante.fxml", "Registro Estudiante");
    }

    public void handleRegistroModerador(ActionEvent actionEvent) {
        abrirVentana("registroModerador.fxml", "Registro Moderador");
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}