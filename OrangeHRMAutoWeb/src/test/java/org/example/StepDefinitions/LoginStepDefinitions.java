
package org.example.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import org.example.Tasks.IniciarSesion;
import org.example.Questions.LoginExitoso;
import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class LoginStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver navegador;

    @Given("que el usuario ingresa a OrangeHRM")
    public void ingresarAOrangeHRM() {

        OnStage.setTheStage(new OnlineCast());

        Actor usuario = OnStage.theActorCalled("Administrador");

        usuario.can(BrowseTheWeb.with(navegador));

        usuario.attemptsTo(
                Open.url("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
        );

        // Evidencia: pantalla de inicio de sesion
        ScreenShot.captureScreenshot(
                navegador,
                Parameters.RutaEvidenciaActual,
                "01_PantallaLogin"
        );
    }

    @When("inicia sesion con usuario {string} y clave {string}")
    public void iniciarSesion(String nombre, String clave) {

        Actor usuario = OnStage.theActorInTheSpotlight();

        usuario.attemptsTo(
                IniciarSesion.conCredenciales(nombre, clave)
        );
    }

    @Then("visualiza el panel principal de OrangeHRM")
    public void validarInicioSesion() {

        Actor usuario = OnStage.theActorInTheSpotlight();

        usuario.should(
                seeThat(LoginExitoso.verificar(), is(true))
        );

        // Evidencia: inicio de sesion exitoso
        ScreenShot.captureScreenshot(
                navegador,
                Parameters.RutaEvidenciaActual,
                "02_LoginExitoso_Dashboard"
        );
    }
}
