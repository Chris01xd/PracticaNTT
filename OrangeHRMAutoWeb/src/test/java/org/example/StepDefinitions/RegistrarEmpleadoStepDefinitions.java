
package org.example.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import org.example.Tasks.IniciarSesion;
import org.example.Tasks.RegistrarEmpleado;
import org.example.Questions.EmpleadoRegistrado;

import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class RegistrarEmpleadoStepDefinitions {

    @Managed(driver = "chrome")
    WebDriver navegador;

    @Given("que el administrador inicia sesion en OrangeHRM")
    public void iniciarSesionAdministrador() {

        OnStage.setTheStage(new OnlineCast());

        Actor administrador = OnStage.theActorCalled("Administrador");

        administrador.can(BrowseTheWeb.with(navegador));

        navegador.get(
                "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        );

        administrador.attemptsTo(
                IniciarSesion.conCredenciales("Admin", "admin123")
        );

        // administrador autenticado
        ScreenShot.captureScreenshot(
                navegador,
                Parameters.RutaEvidenciaActual,
                "01_AdministradorAutenticado"
        );
    }

    @When("registra un empleado con nombre {string} y apellido {string}")
    public void registrarNuevoEmpleado(String nombre, String apellido) {

        Actor administrador = OnStage.theActorInTheSpotlight();

        administrador.attemptsTo(
                RegistrarEmpleado.conDatos(nombre, apellido)
        );
    }

    @Then("visualiza la informacion del empleado {string} {string}")
    public void validarEmpleadoRegistrado(String nombre, String apellido) {

        Actor administrador = OnStage.theActorInTheSpotlight();

        administrador.should(
                seeThat(
                        EmpleadoRegistrado.conDatos(nombre, apellido),
                        is(true)
                )
        );

        //  empleado registrado correctamente
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "02_EmpleadoRegistrado_" + nombre + "_" + apellido
        );
    }
}
