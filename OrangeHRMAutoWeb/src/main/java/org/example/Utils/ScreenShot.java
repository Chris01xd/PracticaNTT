
package org.example.Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    public static String getCurrentDateTime() {
        return new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS")
                .format(new Date());
    }

    public static String getResourcePath(String ruta) {
        return Path.of(
                System.getProperty("user.dir"),
                ruta.replaceFirst("^[/\\\\]+", "")
        ).toString();
    }

    public static void createFolder(String ruta) {
        try {
            Files.createDirectories(Path.of(getResourcePath(ruta)));
        } catch (IOException e) {
            throw new RuntimeException(
                    "No se pudo crear la carpeta de evidencias: " + ruta, e
            );
        }
    }

    public static String captureScreenshot(
            WebDriver driver,
            String carpeta,
            String nombre
    ) {

        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalStateException(
                    "El navegador no permite tomar capturas."
            );
        }

        String nombreSeguro = nombre
                .replaceAll("[^a-zA-Z0-9_-]", "_");

        String nombreArchivo = "evidencia_"
                + getCurrentDateTime()
                + "_"
                + nombreSeguro
                + ".png";

        Path carpetaDestino = Path.of(getResourcePath(carpeta));
        Path destino = carpetaDestino.resolve(nombreArchivo);

        try {
            Files.createDirectories(carpetaDestino);

            File origen = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.copy(
                    origen.toPath(),
                    destino,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return destino.toString();

        } catch (IOException e) {
            throw new RuntimeException(
                    "No se pudo guardar la captura: " + nombreArchivo, e
            );
        }
    }

    public static String captureScreenshot(
            WebDriver driver,
            String carpeta
    ) {
        return captureScreenshot(driver, carpeta, "captura");
    }
}
