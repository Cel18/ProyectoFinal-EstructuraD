package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PanelEstudianteController {

    @FXML
    private void handleVerContenidos(ActionEvent event) {
        System.out.println("Ver contenidos publicados");
        // Lógica para mostrar contenidos publicados
    }

    @FXML
    private void handleVerValoraciones(ActionEvent event) {
        System.out.println("Ver valoraciones");
        // Lógica para mostrar valoraciones
    }

    @FXML
    private void handleVerSugerencias(ActionEvent event) {
        System.out.println("Ver sugerencias");
        // Lógica para mostrar sugerencias
    }

    @FXML
    private void handleSolicitudesAyuda(ActionEvent event) {
        System.out.println("Ver solicitudes de ayuda");
        // Lógica para mostrar solicitudes de ayuda
    }
}