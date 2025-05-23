package proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import proyectofinal.Controladores.*;
import proyectofinal.Modelo.*;
import proyectofinal.Utilidades.Persistencia;

public class App extends Application {
    RedSocial redSocial = Persistencia.cargarRedSocial();

    @Override
    public void start(Stage primaryStage) {
        if (redSocial.getNombre().isEmpty() || redSocial.getEstudiantes().isEmpty() || redSocial.getModeradores().isEmpty()) {
            redSocial = new RedSocial("RedSocialAprendizaje");

            //Datos de prueba
            Estudiante est = new Estudiante("Cel", "Ramírez", "123");
            Moderador mod = new Moderador("Admin", "321");

            redSocial.registrarEstudiante(est);
            redSocial.registrarModerador(mod);

            Persistencia.guardarRedSocial(redSocial);
        }
        System.out.println("== Estudiantes cargados ==");
        redSocial.getEstudiantes().forEach((id, est) -> {
            System.out.println("Nombre: " + est.getNombre() + ", Contraseña: " + est.getContrasena());
        });
        System.out.println("== Moderadores cargados ==");
        redSocial.getModeradores().forEach((id, mod) -> {
            System.out.println("Nombre: " + mod.getNombre() + ", Contraseña: " + mod.getContrasena());
        });
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/login.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/inicio.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/grupoEstudio.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/mensajeria.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/panelEstudiante.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/panelModerador.fxml"));
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setRedSocial(redSocial);

            Scene scene = new Scene(root, 400, 400);
            primaryStage.setTitle(redSocial.getNombre() + " - Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}