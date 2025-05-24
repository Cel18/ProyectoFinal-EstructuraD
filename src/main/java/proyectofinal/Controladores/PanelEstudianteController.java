package proyectofinal.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import proyectofinal.Modelo.RedSocial;

public class PanelEstudianteController{
    private RedSocial redSocial;

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private TabPane tabPane;

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}