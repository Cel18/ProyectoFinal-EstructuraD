package proyectofinal.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import proyectofinal.Modelo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GenerarReporteController {
    private RedSocial redSocial;

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
        StringBuilder reporte = new StringBuilder();

        if (cbContenidosValorados.isSelected()) {
            reporte.append("Contenidos más valorados:\n");
            reporte.append(obtenerContenidosMasValorados());
            reporte.append("\n\n");
        }

        if (cbEstudiantesConexiones.isSelected()) {
            reporte.append("Estudiantes con más conexiones:\n");
            reporte.append(obtenerEstudiantesConMasConexiones());
            reporte.append("\n\n");
        }

        if (cbCaminosCortos.isSelected()) {
            String estudiante1 = parametro1.getText().trim();
            String estudiante2 = parametro2.getText().trim();
            reporte.append("Camino más corto entre " + estudiante1 + " y " + estudiante2 + ":\n");
            reporte.append(obtenerCaminoMasCorto(estudiante1, estudiante2));
            reporte.append("\n\n");
        }

        if (cbDeteccionComunidades.isSelected()) {
            reporte.append("Comunidades de estudio:\n");
            //reporte.append(obtenerComunidadesEstudio());
            reporte.append("\n\n");
        }

        if (cbNivelesParticipacion.isSelected()) {
            reporte.append("Niveles de participación:\n");
            reporte.append(obtenerNivelesParticipacion());
            reporte.append("\n\n");
        }

        areaReporte.setText(reporte.toString());
    }

    private String obtenerContenidosMasValorados() {
        Map<Contenido, Integer> mapaValoraciones = new HashMap<>();
        for (Estudiante e : redSocial.getEstudiantes().values()) {
            for (Valoracion v : e.getValoraciones()) {
                mapaValoraciones.merge(v.getContenido(), v.getPuntuacion(), Integer::sum);
            }
        }

        return mapaValoraciones.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(5)
                .map(e -> e.getKey().getTema() + " - Puntuación total: " + e.getValue())
                .collect(Collectors.joining("\n"));
    }

    private String obtenerEstudiantesConMasConexiones() {
        return redSocial.getEstudiantes().values().stream()
                .sorted((a, b) -> b.getConexiones().getTamanio() - a.getConexiones().getTamanio())
                .limit(5)
                .map(e -> e.getNombreCompleto() + " - Conexiones: " + e.getConexiones().getTamanio())
                .collect(Collectors.joining("\n"));
    }

    private String obtenerCaminoMasCorto(String nombre1, String nombre2) {
        Estudiante est1 = redSocial.getEstudiantes().get(nombre1);
        Estudiante est2 = redSocial.getEstudiantes().get(nombre2);

        if (est1 == null || est2 == null) {
            return "Estudiantes no encontrados.";
        }

        List<Estudiante> camino = redSocial.getGrafo().obtenerCaminoMasCorto(est1, est2);
        if (camino == null || ((java.util.List<?>) camino).isEmpty()) {
            return "No existe un camino entre los estudiantes.";
        }

        return camino.stream().map(Estudiante::getNombreCompleto).collect(Collectors.joining(" -> "));
    }

    /*private String obtenerComunidadesEstudio() {
        List<List<Estudiante>> comunidades = redSocial.getGrafo().detectarGrupos();
        StringBuilder sb = new StringBuilder();

        int i = 1;
        for (List<Estudiante> comunidad : comunidades) {
            sb.append("Comunidad ").append(i++).append(":\n");
            for (Estudiante e : comunidad) {
                sb.append("- ").append(e.getNombreCompleto()).append("\n");
            }
            sb.append("\n");
        }

        return sb.toString();
    }*/

    private String obtenerNivelesParticipacion() {
        StringBuilder sb = new StringBuilder();
        for (Estudiante e : redSocial.getEstudiantes().values()) {
            int publicaciones = e.getContenidosPublicados().getTamanio();
            int valoraciones = e.getValoraciones().getTamanio();
            int total = publicaciones + valoraciones;

            String nivel;
            if (total >= 10) {
                nivel = "Alto";
            } else if (total >= 5) {
                nivel = "Medio";
            } else {
                nivel = "Bajo";
            }

            sb.append(e.getNombreCompleto())
                    .append(" - Participación: ")
                    .append(total)
                    .append(" (")
                    .append(nivel)
                    .append(")\n");
        }
        return sb.toString();
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }
}