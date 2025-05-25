package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import proyectofinal.Modelo.GrupoEstudio;
import proyectofinal.Modelo.RedSocial;

public class GrupoEstudioController {
    private RedSocial redSocial;

    @FXML
    private ListView<String> listaGrupos;

    @FXML
    public void initialize() {
        // Ejemplo datos
        listaGrupos.getItems().addAll(
                "Matemáticas Avanzadas",
                "Programación en Java",
                "Física Cuántica",
                "Historia Universal"
        );
    }

    @FXML
    private void handleUnirseGrupo() {
        String grupo = listaGrupos.getSelectionModel().getSelectedItem();
        if (grupo != null) {
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Unirse al grupo");
            alerta.setHeaderText(null);
            alerta.setContentText("Te has unido al grupo: " + grupo);
            alerta.showAndWait();
        } else {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("Selecciona un grupo para unirte.");
            alerta.showAndWait();
        }
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}