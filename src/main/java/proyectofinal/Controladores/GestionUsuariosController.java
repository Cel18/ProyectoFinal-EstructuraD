package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import proyectofinal.Modelo.RedSocial;

public class GestionUsuariosController {
    private RedSocial redSocial;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ListView<?> listaUsuarios;

    @FXML
    void handleAgregarUsuario(ActionEvent event) {

    }

    @FXML
    void handleBuscarUsuario(ActionEvent event) {

    }

    @FXML
    void handleEliminarUsuario(ActionEvent event) {

    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

}