package proyectofinal.Utilidades;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utilidades {

    private static Utilidades utilidades;
    private Logger logger;

    private Utilidades() {
        logger = Logger.getLogger("RedSocialLogger");
        try {
            FileHandler fileHandler = new FileHandler("Log_Red_Social.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Utilidades getInstance() {
        if (utilidades == null) {
            utilidades = new Utilidades();
        }
        return utilidades;
    }

    public void escribirLog(Level level, String mensaje) {
        logger.log(level, mensaje);
    }
}
