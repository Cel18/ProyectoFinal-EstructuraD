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
        abrirVentana("GestionUsuarios.fxml", "Gestión de Usuarios");
    }

    @FXML
    private void abrirGestionContenidos() {
        abrirVentana("GestionContenidos.fxml", "Gestión de Contenidos");
    }

    @FXML
    private void abrirGenerarReporte() {
        abrirVentana("GenerarReporte.fxml", "Generar Reporte");
    }

    private void abrirVentana(String fxmlArchivo, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxmlArchivo));
            Pane root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);  // Bloquea la ventana padre
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Aquí puedes mostrar una alerta o loguear mejor el error
        }
    }
}