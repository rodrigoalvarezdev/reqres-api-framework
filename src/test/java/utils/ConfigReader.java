package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties propiedades = new Properties();

    static {
        try {
            FileInputStream archivo = new FileInputStream("src/main/resources/config.properties");
            propiedades.load(archivo);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo leer el archivo de configuración", e);
        }
    }

    public static String obtenerPropiedad(String clave) {
        return propiedades.getProperty(clave);
    }
}
