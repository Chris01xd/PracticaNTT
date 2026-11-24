
package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class PetData {

    public static Question<Long> id() {
        return actor -> LastResponse.received()
                .answeredBy(actor)
                .jsonPath()
                .getLong("id");
    }

    public static Question<String> nombre() {
        return actor -> LastResponse.received()
                .answeredBy(actor)
                .jsonPath()
                .getString("name");
    }
}
