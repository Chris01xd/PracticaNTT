
package org.example.StepDefinitions;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;

import org.example.Tasks.RegistrarEmpleado;
import org.example.Tasks.EditarEmpleado;
import org.example.Questions.EmpleadoEditado;

import org.example.Utils.Parameters;
import org.example.Utils.ScreenShot;

import java.util.UUID;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class EditarEmpleadoStepDefinitions {

    @When("registra y modifica el apellido del empleado {string} {string} por {string}")
    public void registrarYEditarEmpleado(
            String nombre,
            String apellido,
            String nuevoApellido) {

        Actor administrador = OnStage.theActorInTheSpotlight();

        String nombrePrueba = nombre + UUID.randomUUID()
                .toString()
                .substring(0, 6);

        // Registrar empleado con el apellido original
        administrador.attemptsTo(
                RegistrarEmpleado.conDatos(nombrePrueba, apellido)
        );

        //  datos originales antes de editar
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "02_AntesDeEditar_" + nombrePrueba + "_" + apellido
        );

        // Modificar y guardar el nuevo apellido
        administrador.attemptsTo(
                EditarEmpleado.conApellido(nuevoApellido)
        );

        //  apellido modificado y guardado
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "03_DespuesDeGuardar_" + nombrePrueba + "_" + nuevoApellido
        );
    }

    @Then("visualiza el apellido actualizado {string}")
    public void validarApellidoActualizado(String nuevoApellido) {

        Actor administrador = OnStage.theActorInTheSpotlight();

        administrador.should(
                seeThat(
                        EmpleadoEditado.conApellido(nuevoApellido),
                        is(true)
                )
        );

        //  cambio persistente tras recargar la ficha
        ScreenShot.captureScreenshot(
                BrowseTheWeb.as(administrador).getDriver(),
                Parameters.RutaEvidenciaActual,
                "04_ApellidoActualizado_" + nuevoApellido
        );
    }
}
