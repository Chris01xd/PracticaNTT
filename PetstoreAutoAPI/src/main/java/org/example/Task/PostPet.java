
package org.example.Task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostPet implements Task {

    private final long id;
    private final String nombre;

    public PostPet(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public static PostPet conDatos(long id, String nombre) {
        return instrumented(PostPet.class, id, nombre);
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
                Post.to("/pet")
                        .with(request -> request
                                .contentType("application/json")
                                .accept("application/json")
                                .body(body)
                                .log().all())
        );
    }
}
