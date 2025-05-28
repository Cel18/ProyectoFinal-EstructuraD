package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

import java.io.IOException;
import java.util.Optional;

public class PanelEstudianteController{
    private RedSocial redSocial;

    @FXML
    private ImageView imagenPerfil;

    @FXML
    private ListView<Contenido> listaContenidos;

    @FXML
    private TextField tfBuscarContenido;

    @FXML
    private Button btnBuscarContenido;

    @FXML
    public void initialize() {
        btnBuscarContenido.setOnAction(e -> buscarContenidos());
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
        cargarContenidos();
    }

    private void buscarContenidos() {
        String textoBusqueda = tfBuscarContenido.getText().trim().toLowerCase();

        if (textoBusqueda.isEmpty()) {
            cargarContenidos();
            return;
        }

        listaContenidos.getItems().clear();

        for (Estudiante est : redSocial.getEstudiantes().values()) {
            ListaEnlazada<Contenido> lista = est.getContenidosPublicados();

            NodoContenido<Contenido> nodo = lista.getInicial();
            while (nodo != null) {
                Contenido c = nodo.getContenido();

                boolean coincideTema = c.getTema().toLowerCase().contains(textoBusqueda);
                boolean coincideAutor = c.getAutor().getNombre().toLowerCase().contains(textoBusqueda);
                boolean coincideTipo = c.getTipo().toString().toLowerCase().contains(textoBusqueda);

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
    private void abrirGrupoEstudio() {
        abrirVentana("grupoEstudio.fxml", "Grupos de Estudio");
    }

    @FXML
    private void abrirMensajeria() {
        abrirVentana("mensajeria.fxml", "Mensajeria");
    }

    public void publicar(ActionEvent actionEvent) {
        abrirVentana("publicarContenido.fxml", "Publicar Contenido");
    }

    private void abrirVentana(String fxmlArchivo, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/" + fxmlArchivo));
            Pane root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof GrupoEstudioController) {
                ((GrupoEstudioController) controller).setRedSocial(redSocial);
            } else  if (controller instanceof MensajeriaController) {
                ((MensajeriaController) controller).setRedSocial(redSocial);
            } else  if (controller instanceof PublicarContenidoController) {
                ((PublicarContenidoController) controller).setRedSocial(redSocial);
            }

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            cargarContenidos();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void irAPerfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/perfilEstudiante.fxml"));
            Pane root = loader.load();

            PerfilEstudianteController controller = loader.getController();
            controller.setRedSocial(redSocial);
            controller.cargarPerfil();
            controller.setVistaAnterior("/proyectofinal/panelEstudiante.fxml");

            Stage stage = (Stage) imagenPerfil.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Perfil Estudiante");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarContenidos() {
        if (redSocial != null) {
            listaContenidos.getItems().clear();

            for (Estudiante est : redSocial.getEstudiantes().values()) {
                for (var contenido : est.getContenidosPublicados()) {
                    listaContenidos.getItems().add(contenido);
                }
            }
        } else {
            mostrarAlerta("Error", "Red social no disponible.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void eliminarContenido() {
        Contenido contenidoSeleccionado = listaContenidos.getSelectionModel().getSelectedItem();

        if (contenidoSeleccionado == null) {
            mostrarAlerta("Advertencia", "Debe seleccionar un contenido para eliminar.");
            return;
        }

        Estudiante estudianteActivo = redSocial.getEstudianteActivo();
        if (estudianteActivo == null) {
            mostrarAlerta("Error", "No hay estudiante activo.");
            return;
        }

        if (!contenidoSeleccionado.getAutor().equals(estudianteActivo)) {
            mostrarAlerta("Error", "Solo puede eliminar contenidos propios.");
            return;
        }

        boolean confirmar = mostrarConfirmacion("Confirmar eliminación",
                "¿Está seguro que desea eliminar el contenido seleccionado?");
        if (!confirmar) return;

        redSocial.eliminarContenido(estudianteActivo, contenidoSeleccionado);

        listaContenidos.getItems().remove(contenidoSeleccionado);

        mostrarAlerta("Éxito", "Contenido eliminado correctamente.");
    }

    private boolean mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    @FXML
    private void valorarContenido(ActionEvent event) {
        Contenido contenidoSeleccionado = listaContenidos.getSelectionModel().getSelectedItem();

        if (contenidoSeleccionado == null) {
            mostrarAlerta("Valorar contenido", "Por favor, seleccione un contenido para valorar.");
            return;
        }

        TextInputDialog dialogPuntuacion = new TextInputDialog();
        dialogPuntuacion.setTitle("Valorar contenido");
        dialogPuntuacion.setHeaderText("Ingrese una puntuación del 1 al 5");
        dialogPuntuacion.setContentText("Puntuación:");

        Optional<String> resultadoPuntuacion = dialogPuntuacion.showAndWait();
        if (!resultadoPuntuacion.isPresent()) {
            return;
        }

        int puntuacion;
        try {
            puntuacion = Integer.parseInt(resultadoPuntuacion.get());
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La puntuación debe ser un número válido.");
            return;
        }

        if (puntuacion < 1 || puntuacion > 5) {
            mostrarAlerta("Error", "La puntuación debe estar entre 1 y 5.");
            return;
        }

        TextInputDialog dialogComentario = new TextInputDialog();
        dialogComentario.setTitle("Valorar contenido");
        dialogComentario.setHeaderText("Ingrese un comentario (opcional)");
        dialogComentario.setContentText("Comentario:");

        Optional<String> resultadoComentario = dialogComentario.showAndWait();
        String comentario = resultadoComentario.orElse("");

        Estudiante estudianteActivo = redSocial.getEstudianteActivo();
        if (estudianteActivo != null) {
            estudianteActivo.valorarContenido(contenidoSeleccionado, puntuacion, comentario);
            mostrarAlerta("Valoración realizada", "¡Contenido valorado correctamente!");
        }
    }
}