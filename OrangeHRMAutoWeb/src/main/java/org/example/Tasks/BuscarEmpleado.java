
package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.example.Pages.BuscarEmpleadoPage.*;

public class BuscarEmpleado implements Task {

    private final String nombre;

    public BuscarEmpleado(String nombre) {
        this.nombre = nombre;
    }

    public static Performable conNombre(String nombre) {
        return Tasks.instrumented(BuscarEmpleado.class, nombre);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(

                Open.url(
                        "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList"
                ),

                WaitUntil.the(TXT_BUSCAR_EMPLEADO, isVisible())
                        .forNoMoreThan(20).seconds(),

                Enter.theValue(nombre).into(TXT_BUSCAR_EMPLEADO),

                Click.on(BTN_BUSCAR),

                WaitUntil.the(TABLA_RESULTADOS, isVisible())
                        .forNoMoreThan(20).seconds()
        );
    }
}
