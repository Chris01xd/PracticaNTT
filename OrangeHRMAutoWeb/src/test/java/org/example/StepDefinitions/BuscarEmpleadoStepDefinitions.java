
package org.example.StepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;

import org.example.Tasks.RegistrarEmpleado;
import org.example.Tasks.BuscarEmpleado;
import org.example.Questions.EmpleadoEncontrado;

import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

import java.util.UUID;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class BuscarEmpleadoStepDefinitions {

    private String nombrePrueba;

    @When("registra y busca al empleado {string} {string}")
    public void registrarYBuscarEmpleado(
            String nombre, String apellido) {

        Actor administrador = OnStage.theActorInTheSpotlight();

        nombrePrueba = nombre + UUID.randomUUID()
                .toString()
                .substring(0, 6);

        // Registrar un empleado para realizar la busqueda
        administrador.attemptsTo(
                RegistrarEmpleado.conDatos(nombrePrueba, apellido)
        );

        // Evidencia: empleado preparado para la busqueda
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "02_EmpleadoCreado_" + nombrePrueba
        );

        // Buscar al empleado registrado
        administrador.attemptsTo(
                BuscarEmpleado.conNombre(nombrePrueba)
        );
    }

    @Then("visualiza al empleado registrado en los resultados")
    public void validarEmpleadoEncontrado() {

        Actor administrador = OnStage.theActorInTheSpotlight();

        administrador.should(
                seeThat(
                        EmpleadoEncontrado.conNombre(nombrePrueba),
                        is(true)
                )
        );

        // Evidencia: empleado encontrado en los resultados
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "03_EmpleadoEncontrado_" + nombrePrueba
        );
    }
}
