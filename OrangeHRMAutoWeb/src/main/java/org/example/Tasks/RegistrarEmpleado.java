
package org.example.Tasks;

import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.UUID;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.example.Pages.EmpleadoPage.*;

public class RegistrarEmpleado implements Task {

    private final String nombre;
    private final String apellido;

    public RegistrarEmpleado(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public static Performable conDatos(String nombre, String apellido) {
        return Tasks.instrumented(RegistrarEmpleado.class, nombre, apellido);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String idEmpleado = UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 8);

        actor.attemptsTo(

                Open.url(
                        "https://opensource-demo.orangehrmlive.com/web/index.php/pim/addEmployee"
                ),

                WaitUntil.the(TXT_NOMBRE, isVisible())
                        .forNoMoreThan(20).seconds(),

                Enter.theValue(nombre).into(TXT_NOMBRE),

                Enter.theValue(apellido).into(TXT_APELLIDO),

                ReemplazarIdEmpleado.con(idEmpleado),

                Click.on(BTN_GUARDAR),

                WaitUntil.the(TITULO_DATOS_PERSONALES, isVisible())
                        .forNoMoreThan(20).seconds()
        );
    }
}
