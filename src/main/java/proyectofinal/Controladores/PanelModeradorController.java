package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import proyectofinal.Modelo.GrafoAfinidad;
import proyectofinal.Modelo.RedSocial;

import java.io.IOException;

public class PanelModeradorController {
    private RedSocial redSocial;

    @FXML
    private Pane panelGrafo;

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
        dibujarGrafo();
    }

    private void dibujarGrafo() {
        panelGrafo.getChildren().clear();

        GrafoAfinidad grafoAfinidad = redSocial.getGrafo();
        if (grafoAfinidad == null) return;

        Graph graph = grafoAfinidad.getGraph();

        FxViewer viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.enableAutoLayout();

        FxViewPanel viewPanel = (FxViewPanel) viewer.addDefaultView(false);

        viewPanel.setMinSize(panelGrafo.getPrefWidth(), panelGrafo.getPrefHeight());
        viewPanel.setMaxSize(panelGrafo.getPrefWidth(), panelGrafo.getPrefHeight());

        panelGrafo.getChildren().add(viewPanel);
    }

    @FXML
    private void abrirGestionUsuarios() {
        abrirVentana("gestionUsuarios.fxml", "Gestión de Usuarios");
    }

    @FXML
    private void abrirGestionContenidos() {
        abrirVentana("gestionContenidos.fxml", "Gestión de Contenidos");
    }

    @FXML
    private void abrirGenerarReporte() {
        abrirVentana("generarReporte.fxml", "Generar Reporte");
    }

    @FXML
    private void irAPerfilModerador() {
        irAPerfil();
    }

    private void abrirVentana(String fxmlArchivo, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxmlArchivo));
            Pane root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof GestionUsuariosController) {
                ((GestionUsuariosController) controller).setRedSocial(redSocial);
            } else  if (controller instanceof GestionContenidosController) {
                ((GestionContenidosController) controller).setRedSocial(redSocial);
            } else  if (controller instanceof GenerarReporteController) {
                ((GenerarReporteController) controller).setRedSocial(redSocial);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/perfilModerador.fxml"));
            Pane root = loader.load();

            PerfilModeradorController controller = loader.getController();
            controller.setRedSocial(redSocial);
            controller.cargarPerfil();

            Stage stage = (Stage) panelGrafo.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Perfil Moderador");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}