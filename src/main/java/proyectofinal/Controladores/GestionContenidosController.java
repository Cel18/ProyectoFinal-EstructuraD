package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectofinal.Modelo.*;

import java.io.IOException;
import java.util.Optional;

public class GestionContenidosController {
    private RedSocial redSocial;

    @FXML
    private TextField campoBusqueda;

    @FXML
    private ListView<Contenido> listaContenidos;

    @FXML
    void handleAgregarContenido(ActionEvent event) {
        abrirVentana("/proyectofinal/publicarContenido.fxml", "Publicar Contenido");
    }

    @FXML
    void handleBuscarContenido(ActionEvent event) {
        String texto = campoBusqueda.getText().trim().toLowerCase();
        listaContenidos.getItems().clear();

        if (texto.isEmpty()) {
            cargarContenidos();
            return;
        }

        for (Estudiante est : redSocial.getEstudiantes().values()) {
            ListaEnlazada<Contenido> lista = est.getContenidosPublicados();
            NodoContenido<Contenido> nodo = lista.getInicial();

            while (nodo != null) {
                Contenido c = nodo.getContenido();

                boolean coincideTema = c.getTema().toLowerCase().contains(texto);
                boolean coincideAutor = c.getAutor().getNombre().toLowerCase().contains(texto);
                boolean coincideTipo = c.getTipo().toString().toLowerCase().contains(texto);

                if (coincideTema || coincideAutor || coincideTipo) {
                    listaContenidos.getItems().add(c);
                }
                nodo = nodo.getDerecho();
            }
        }

        if (listaContenidos.getItems().isEmpty()) {
            mostrarAlerta("Búsqueda", "No se encontraron contenidos que coincidan.");
        }
    }

    @FXML
    void handleEliminarContenido(ActionEvent event) {
        Contenido seleccionado = listaContenidos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Advertencia", "Debe seleccionar un contenido para eliminar.");
            return;
        }

        boolean confirmar = mostrarConfirmacion("Confirmar eliminación", "¿Desea eliminar el contenido seleccionado?");
        if (!confirmar) return;

        Estudiante autor = seleccionado.getAutor();
        redSocial.eliminarContenido(autor, seleccionado);
        listaContenidos.getItems().remove(seleccionado);

        mostrarAlerta("Éxito", "Contenido eliminado correctamente.");
    }

    public void cargarContenidos() {
        if (redSocial != null) {
            listaContenidos.getItems().clear();

            for (Estudiante est : redSocial.getEstudiantes().values()) {
                ListaEnlazada<Contenido> lista = est.getContenidosPublicados();
                NodoContenido<Contenido> nodo = lista.getInicial();

                while (nodo != null) {
                    Contenido contenido = nodo.getContenido();
                    listaContenidos.getItems().add(contenido);
                    nodo = nodo.getDerecho();
                }
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

    private boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
