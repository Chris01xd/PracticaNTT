
package org.example.Task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetPet implements Task {

    private final long id;

    public GetPet(long id) {
        this.id = id;
    }

    public static GetPet conId(long id) {
        return instrumented(GetPet.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/pet/" + id)
        );
    }
}
