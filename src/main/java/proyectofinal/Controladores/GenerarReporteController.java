package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class GenerarReporteController {

    @FXML
    private TextArea areaReporte;

    @FXML
    private CheckBox cbCaminosCortos;

    @FXML
    private CheckBox cbContenidosValorados;

    @FXML
    private CheckBox cbDeteccionComunidades;

    @FXML
    private CheckBox cbEstudiantesConexiones;

    @FXML
    private CheckBox cbNivelesParticipacion;

    @FXML
    private TextField parametro1;

    @FXML
    private TextField parametro2;

    @FXML
    void handleGenerarReporte(ActionEvent event) {

    }
}