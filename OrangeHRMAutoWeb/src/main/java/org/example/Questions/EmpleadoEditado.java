
package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

import static org.example.Pages.EditarEmpleadoPage.*;

public class EmpleadoEditado implements Question<Boolean> {

    private final String nuevoApellido;

    public EmpleadoEditado(String nuevoApellido) {
        this.nuevoApellido = nuevoApellido;
    }

    public static EmpleadoEditado conApellido(String nuevoApellido) {
        return new EmpleadoEditado(nuevoApellido);
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        var navegador = BrowseTheWeb.as(actor).getDriver();

        // Recargar la ficha para verificar que el cambio se guardo
        navegador.navigate().refresh();

        WebDriverWait espera = new WebDriverWait(
                navegador,
                Duration.ofSeconds(20)
        );

        try {
            return espera.until(driver -> {

                boolean fichaVisible =
                        TITULO_DATOS_PERSONALES
                                .resolveFor(actor)
                                .isVisible();

                String apellidoActual =
                        TXT_APELLIDO
                                .resolveFor(actor)
                                .getAttribute("value");

                return fichaVisible
                        && nuevoApellido.equals(apellidoActual);
            });

        } catch (TimeoutException e) {
            return false;
        }
    }
}
