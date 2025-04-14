package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
	 public static String leerJsonComoString(String pathRelativo) {
	        try {
	            return new String(Files.readAllBytes(Paths.get(pathRelativo)));
	        } catch (IOException e) {
	            throw new RuntimeException("Error al leer el archivo JSON: " + pathRelativo, e);
	        }
	    }
}
