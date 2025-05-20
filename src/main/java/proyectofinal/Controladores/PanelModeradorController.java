package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;

public class PanelModeradorController {

    @FXML
    private ListView<String> listaUsuarios;

    @FXML
    private Pane panelGrafo;

    @FXML
    public void initialize() {
        listaUsuarios.getItems().addAll("Usuario1", "Usuario2", "Usuario3");
    }

    @FXML
    private void handleEliminarUsuario() {
        String usuario = listaUsuarios.getSelectionModel().getSelectedItem();
        if (usuario != null) {
            listaUsuarios.getItems().remove(usuario);
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Usuario eliminado");
            alerta.setHeaderText(null);
            alerta.setContentText("Se eliminó al usuario: " + usuario);
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Atención");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona un usuario para eliminar.");
            alerta.showAndWait();
        }
    }
}