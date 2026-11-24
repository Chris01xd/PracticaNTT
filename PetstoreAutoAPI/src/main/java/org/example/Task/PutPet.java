
package org.example.Task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutPet implements Task {

    private final long id;
    private final String nombre;

    public PutPet(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static PutPet conDatos(long id, String nombre) {
        return instrumented(PutPet.class, id, nombre);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String body = """
                {
                  "id": %d,
                  "name": "%s",
                  "photoUrls": [],
                  "status": "available"
                }
                """.formatted(id, nombre);

        actor.attemptsTo(
                Put.to("/pet")
                        .with(request -> request
                                .contentType("application/json")
                                .accept("application/json")
                                .body(body)
                                .log().all())
        );
    }
}
