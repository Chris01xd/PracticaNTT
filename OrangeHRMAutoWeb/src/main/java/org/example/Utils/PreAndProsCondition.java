
package org.example.Utils;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class PreAndProsCondition {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(PreAndProsCondition.class);

    private int numeroCaptura = 0;

    @Before
    public void beforeScenario(Scenario scenario) {

        LOGGER.info("======================================");
        LOGGER.info("INICIANDO: {}", scenario.getName());

        OnStage.setTheStage(new OnlineCast());

        numeroCaptura = 0;

        String nombreEscenario = scenario.getName()
                .replaceAll("[^a-zA-Z0-9_-]", "_");

        String carpeta = Parameters.RutaEvidencia
                + ScreenShot.getCurrentDateTime()
                + "_"
                + nombreEscenario
                + "/";

        Parameters.RutaEvidenciaActual = carpeta;

        ScreenShot.createFolder(carpeta);

        LOGGER.info("Carpeta de evidencias: {}", carpeta);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {

        try {
            WebDriver navegador = getDriver();

            if (navegador == null) {
                LOGGER.warn(
                        "No hay navegador disponible para capturar evidencia."
                );
                return;
            }

            numeroCaptura++;

            String nombrePaso = String.format(
                    "paso_%02d_%s",
                    numeroCaptura,
                    scenario.isFailed() ? "FAIL" : "PASS"
            );

            String ruta = ScreenShot.captureScreenshot(
                    navegador,
                    Parameters.RutaEvidenciaActual,
                    nombrePaso
            );

            LOGGER.info("Evidencia guardada: {}", ruta);

        } catch (Exception e) {
            LOGGER.warn(
                    "No se pudo capturar la evidencia: {}",
                    e.getMessage()
            );
        }
    }

    @After
    public void afterScenario(Scenario scenario) {

        LOGGER.info(
                "FINALIZANDO: {} | Estado: {}",
                scenario.getName(),
                scenario.isFailed() ? "FAIL" : "PASS"
        );

        Parameters.RutaEvidenciaActual = "";
    }
}
