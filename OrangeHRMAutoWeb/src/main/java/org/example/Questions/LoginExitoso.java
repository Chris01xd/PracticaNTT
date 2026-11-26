
package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.example.Pages.LoginPage.LBL_DASHBOARD;

public class LoginExitoso implements Question<Boolean> {

    public static LoginExitoso verificar() {
        return new LoginExitoso();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return LBL_DASHBOARD.resolveFor(actor).isVisible();
    }
}
