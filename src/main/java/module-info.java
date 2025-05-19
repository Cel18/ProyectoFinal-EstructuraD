module com.example.proyectofinalestructurad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens proyectofinal to javafx.fxml;
    exports proyectofinal;
    exports proyectofinal.Controladores;
    opens proyectofinal.Controladores to javafx.fxml;
}