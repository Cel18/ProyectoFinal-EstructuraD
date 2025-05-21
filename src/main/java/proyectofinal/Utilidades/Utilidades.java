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
            //Cambia mi usuario "juans" por el tuyo cuando vayas a probar persistencia :)
            FileHandler fileHandler = new FileHandler("C:\\Users\\juans\\Downloads\\Proyecto_Final_ED\\Log_Red_Social.log", true);
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
