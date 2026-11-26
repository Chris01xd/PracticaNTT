
package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.example.Pages.BuscarEmpleadoPage.MENSAJE_SIN_RESULTADOS;

public class EmpleadoNoEncontrado implements Question<Boolean> {

    public static EmpleadoNoEncontrado verificar() {
        return new EmpleadoNoEncontrado();
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        actor.attemptsTo(
                WaitUntil.the(MENSAJE_SIN_RESULTADOS, isVisible())
                        .forNoMoreThan(20).seconds()
        );

        String mensaje = MENSAJE_SIN_RESULTADOS
                .resolveFor(actor)
                .getText();

        return mensaje.trim().equalsIgnoreCase("No Records Found");
    }
}
