package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import proyectofinal.Modelo.*;

public class PublicarContenidoController {
    private RedSocial redSocial;

    @FXML
    private TextField txtTema;

    @FXML
    private ComboBox<TipoContenido> comboTipo;

    @FXML
    private TextField txtAutor;

    @FXML
    private void initialize() {
        comboTipo.getItems().setAll(TipoContenido.values());
    }

    @FXML
    private void publicarContenido(ActionEvent event) {
        if (redSocial != null) {
            var estudiante = redSocial.getEstudianteActivo();

            if (estudiante != null) {
                String tema = txtTema.getText().trim();
                TipoContenido tipo = comboTipo.getValue();

                if (tema.isEmpty()) {
                    mostrarAlerta("Error", "El tema no puede estar vacío.");
                    return;
                }

                if (tipo == null) {
                    mostrarAlerta("Error", "Debes seleccionar un tipo de contenido.");
                    return;
                }

                Estudiante autor = estudiante;
                Contenido nuevoContenido = new Contenido(tema, autor, tipo);

                redSocial.publicarContenido(autor, nuevoContenido);

                txtTema.clear();
                comboTipo.getSelectionModel().clearSelection();

                mostrarAlerta("Éxito", "Contenido publicado correctamente.");
            } else {
                mostrarAlerta("Error", "No hay estudiante activo.");
            }
        } else {
            mostrarAlerta("Error", "No se pudo cargar la red social.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
        var estudiante = redSocial.getEstudianteActivo();
        if (estudiante != null) {
            txtAutor.setText(estudiante.toString());
        }
    }
}