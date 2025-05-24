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
            Parent root = loader.load();

            LoginController loginController = loader.getController();
            loginController.setRedSocial(redSocial);

            Scene scene = new Scene(root);
            primaryStage.setTitle(redSocial.getNombre());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/mensajeria.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle(redSocial.getNombre());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        launch(args);
    }
}