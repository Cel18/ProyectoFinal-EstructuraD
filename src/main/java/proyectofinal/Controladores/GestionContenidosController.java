package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import proyectofinal.Modelo.RedSocial;

public class GestionContenidosController {
    private RedSocial redSocial;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ListView<?> listaContenidos;

    @FXML
    void handleAgregarContenido(ActionEvent event) {

    }

    @FXML
    void handleBuscarContenido(ActionEvent event) {

    }

    @FXML
    void handleEliminarContenido(ActionEvent event) {

    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}
