
package org.example.StepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;

import org.example.Tasks.IniciarSesion;
import org.example.Questions.LoginFallido;

import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class LoginNegativoStepDefinitions {

    @When("intenta iniciar sesion con usuario {string} y clave {string}")
    public void intentarInicioSesion(String usuario, String clave) {

        Actor usuarioActual = OnStage.theActorInTheSpotlight();

        usuarioActual.attemptsTo(
                IniciarSesion.conCredenciales(usuario, clave)
        );
    }

    @Then("visualiza el mensaje de credenciales invalidas")
    public void validarMensajeError() {

        Actor usuarioActual = OnStage.theActorInTheSpotlight();

        usuarioActual.should(
                seeThat(
                        LoginFallido.verificar(),
                        is(true)
                )
        );

        // mensaje de credenciales incorrectas
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(usuarioActual).getDriver(),
                Parameters.RutaEvidenciaActual,
                "02_LoginRechazado_InvalidCredentials"
        );
    }
}
