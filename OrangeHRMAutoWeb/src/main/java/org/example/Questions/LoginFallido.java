
package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.example.Pages.LoginPage.MSG_ERROR;

public class LoginFallido implements Question<Boolean> {

    public static LoginFallido verificar() {
        return new LoginFallido();
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        actor.attemptsTo(
                WaitUntil.the(MSG_ERROR, isVisible())
                        .forNoMoreThan(20).seconds()
        );

        String mensaje = MSG_ERROR.resolveFor(actor).getText();

        return mensaje.trim().equalsIgnoreCase(
                "Invalid credentials"
        );
    }
}
