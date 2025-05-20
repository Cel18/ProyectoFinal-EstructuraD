package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InicioController {

    @FXML
    private void handleExplorarContenidos(ActionEvent event) {
        System.out.println("Explorar contenidos presionado");
        // Aquí va la lógica para ir a la vista de contenidos
    }

    @FXML
    private void handleAccederPerfil(ActionEvent event) {
        System.out.println("Acceder a perfil presionado");
        // Aquí va la lógica para ir a la vista de perfil
    }
}