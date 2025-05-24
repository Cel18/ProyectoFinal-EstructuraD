package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import proyectofinal.Modelo.RedSocial;

public class PanelModeradorController {
    private RedSocial redSocial;

    @FXML
    private Pane panelGrafo;

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}