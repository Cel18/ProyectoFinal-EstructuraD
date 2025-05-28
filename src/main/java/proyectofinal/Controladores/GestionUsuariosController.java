package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectofinal.Modelo.Estudiante;
import proyectofinal.Modelo.RedSocial;

import java.io.IOException;

public class GestionUsuariosController {
    private RedSocial redSocial;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ListView<String> listaUsuarios;

    @FXML
    void handleAgregarUsuario(ActionEvent event) {
        abrirVentana("/proyectofinal/registroEstudiante.fxml", "Registro Estudiante");
    }

    @FXML
    void handleBuscarUsuario(ActionEvent event) {
        String nombre = campoBusqueda.getText().trim();
        if (!nombre.isEmpty()) {
            Estudiante estudiante = redSocial.buscarEstudiante(nombre);
            listaUsuarios.getItems().clear();
            if (estudiante != null) {
                listaUsuarios.getItems().add(estudiante.getNombreCompleto());
            } else {
                listaUsuarios.getItems().add("No se encontró el estudiante: " + nombre);
            }
        }
    }

    @FXML
    void handleEliminarUsuario(ActionEvent event) {
        String seleccionado = listaUsuarios.getSelectionModel().getSelectedItem();
        if (seleccionado != null && redSocial.getEstudiantes().containsKey(seleccionado)) {
            redSocial.getEstudiantes().remove(seleccionado);
            mostrarEstudiantes();
        }
    }

    public void mostrarEstudiantes() {
        if (redSocial != null) {
            listaUsuarios.getItems().clear();

            for (Estudiante est : redSocial.getEstudiantes().values()) {
                listaUsuarios.getItems().add(est.getNombreCompleto());
            }
        } else {
            mostrarAlerta("Error", "Red social no disponible.");
        }
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    private void abrirVentana(String fxmlPath, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof RegistroEstudianteController) {
                ((RegistroEstudianteController) controller).setRedSocial(redSocial);
            } else if (controller instanceof RegistroModeradorController) {
                ((RegistroModeradorController) controller).setRedSocial(redSocial);
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}