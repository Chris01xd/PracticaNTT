
package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Pages.EmpleadoPage.*;

public class EmpleadoRegistrado implements Question<Boolean> {

    private final String nombre;
    private final String apellido;

    public EmpleadoRegistrado(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public static EmpleadoRegistrado conDatos(
            String nombre, String apellido) {

        return new EmpleadoRegistrado(nombre, apellido);
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        WebDriverWait espera = new WebDriverWait(
                BrowseTheWeb.as(actor).getDriver(),
                Duration.ofSeconds(20)
        );

        boolean datosCorrectos;

        try {
            datosCorrectos = espera.until(driver -> {

                boolean fichaVisible =
                        TITULO_DATOS_PERSONALES
                                .resolveFor(actor).isVisible();

                String nombreActual =
                        TXT_NOMBRE_REGISTRADO
                                .resolveFor(actor)
                                .getAttribute("value");

                String apellidoActual =
                        TXT_APELLIDO_REGISTRADO
                                .resolveFor(actor)
                                .getAttribute("value");

                return fichaVisible
                        && nombre.equals(nombreActual)
                        && apellido.equals(apellidoActual);
            });

        } catch (org.openqa.selenium.TimeoutException e) {

            datosCorrectos = false;
        }

        return datosCorrectos;
    }
}
