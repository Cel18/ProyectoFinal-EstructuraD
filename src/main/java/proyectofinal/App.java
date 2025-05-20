package proyectofinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/login.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/inicio.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/grupoEstudio.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/mensajeria.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/panelEstudiante.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyectofinal/panelModerador.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 400, 400); // puedes ajustar el tamaño
            primaryStage.setTitle("Red de Aprendizaje - Login");
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