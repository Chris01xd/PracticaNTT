
package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Performable;

import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.example.Pages.EmpleadoPage.TXT_ID_EMPLEADO;

public class ReemplazarIdEmpleado implements Task {

    private final String idEmpleado;

    public ReemplazarIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public static Performable con(String idEmpleado) {
        return Tasks.instrumented(
                ReemplazarIdEmpleado.class,
                idEmpleado
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(TXT_ID_EMPLEADO, isVisible())
                        .forNoMoreThan(20).seconds()
        );

        var campo = TXT_ID_EMPLEADO.resolveFor(actor);

        campo.click();

        campo.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        campo.sendKeys(Keys.BACK_SPACE);

        campo.sendKeys(idEmpleado);

        String idActual = campo.getAttribute("value");

        if (!idEmpleado.equals(idActual)) {
            throw new AssertionError(
                    "ID esperado: " + idEmpleado
                            + " | ID obtenido: " + idActual
            );
        }
    }
}
