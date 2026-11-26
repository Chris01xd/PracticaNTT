
package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Performable;

import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.targets.Target;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.example.Pages.EditarEmpleadoPage.*;

public class EditarEmpleado implements Task {

    private final String nuevoApellido;

    public EditarEmpleado(String nuevoApellido) {
        this.nuevoApellido = nuevoApellido;
    }

    public static Performable conApellido(String nuevoApellido) {
        return Tasks.instrumented(
                EditarEmpleado.class,
                nuevoApellido
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(TITULO_DATOS_PERSONALES, isVisible())
                        .forNoMoreThan(20).seconds(),

                WaitUntil.the(TXT_APELLIDO, isVisible())
                        .forNoMoreThan(20).seconds()
        );

        // Reemplazar completamente el apellido anterior
        var campoApellido = TXT_APELLIDO.resolveFor(actor);

        campoApellido.click();

        campoApellido.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        campoApellido.sendKeys(Keys.BACK_SPACE);
        campoApellido.sendKeys(nuevoApellido);
        campoApellido.sendKeys(Keys.TAB);

        // Comprobar que se escribio correctamente
        String apellidoEscrito =
                TXT_APELLIDO.resolveFor(actor)
                        .getAttribute("value");

        if (!nuevoApellido.equals(apellidoEscrito)) {
            throw new AssertionError(
                    "Apellido esperado: " + nuevoApellido
                            + " | Apellido escrito: " + apellidoEscrito
            );
        }

        actor.attemptsTo(
                Click.on(BTN_GUARDAR_CAMBIOS),

                WaitUntil.the(
                        Target.the("Confirmacion de guardado")
                                .located(By.xpath(
                                        "//*[contains(@class,'oxd-toast')]" +
                                                "[contains(.,'Successfully Updated')]"
                                )),
                        isVisible()
                ).forNoMoreThan(20).seconds()
        );
    }
}
