package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectofinal.Modelo.Contenido;
import proyectofinal.Modelo.Estudiante;
import proyectofinal.Modelo.RedSocial;

import java.io.IOException;

public class PanelEstudianteController{
    private RedSocial redSocial;

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private ListView<String> listaContenidos;


    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
        cargarContenidos();
    }

    @FXML
    private void abrirGrupoEstudio() {
        abrirVentana("grupoEstudio.fxml", "Grupos de Estudio");
    }

    @FXML
    private void abrirMensajeria() {
        abrirVentana("mensajeria.fxml", "Mensajeria");
    }

    private void abrirVentana(String fxmlArchivo, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxmlArchivo));
            Pane root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof GrupoEstudioController) {
                ((GrupoEstudioController) controller).setRedSocial(redSocial);
            } else  if (controller instanceof MensajeriaController) {
                ((MensajeriaController) controller).setRedSocial(redSocial);
            } else  if (controller instanceof PublicarContenidoController) {
                ((PublicarContenidoController) controller).setRedSocial(redSocial);
            }

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/perfilEstudiante.fxml"));
            Pane root = loader.load();

            PerfilEstudianteController controller = loader.getController();
            controller.setRedSocial(redSocial);
            controller.cargarPerfil();
            controller.setVistaAnterior("/proyectofinal/panelEstudiante.fxml");

            Stage stage = (Stage) imagenPerfil.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Perfil Estudiante");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void publicar(ActionEvent actionEvent) {
        abrirVentana("publicarContenido.fxml", "Publicar Contenido");
    }

    public void cargarContenidos() {
        if (redSocial != null) {
            var estudiante = redSocial.getEstudianteActivo();
            if (estudiante != null) {
                listaContenidos.getItems().clear();

                for (var contenido : estudiante.getContenidosPublicados()) {
                    listaContenidos.getItems().add(contenido.toString());
                }

            } else {
                mostrarAlerta("Error", "No hay estudiante activo.");
            }
        } else {
            mostrarAlerta("Error", "Red social no disponible.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}