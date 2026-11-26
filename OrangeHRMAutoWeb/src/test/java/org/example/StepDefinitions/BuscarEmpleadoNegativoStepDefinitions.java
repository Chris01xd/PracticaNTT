
package org.example.StepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;

import org.example.Tasks.BuscarEmpleado;
import org.example.Questions.EmpleadoNoEncontrado;

import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

import java.util.UUID;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class BuscarEmpleadoNegativoStepDefinitions {

    @When("busca un empleado inexistente con nombre {string}")
    public void buscarEmpleadoInexistente(String nombre) {

        Actor administrador = OnStage.theActorInTheSpotlight();

        // Nombre unico para evitar coincidencias
        String nombreInexistente = nombre
                + UUID.randomUUID().toString().replace("-", "");

        administrador.attemptsTo(
                BuscarEmpleado.conNombre(nombreInexistente)
        );

        //  busqueda de empleado inexistente
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "02_BusquedaEmpleadoInexistente"
        );
    }

    @Then("visualiza el mensaje de que no existen registros")
    public void validarSinResultados() {

        Actor administrador = OnStage.theActorInTheSpotlight();

        administrador.should(
                seeThat(
                        EmpleadoNoEncontrado.verificar(),
                        is(true)
                )
        );

        // mensaje sin registros encontrados
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "03_NoRecordsFound"
        );
    }
}
