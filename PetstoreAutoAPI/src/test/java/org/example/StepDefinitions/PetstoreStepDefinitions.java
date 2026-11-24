
package org.example.StepDefinitions;

import nonapi.io.github.classgraph.json.JSONUtils;
import org.example.Task.PostPet;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.example.Questions.ResponseCode;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import org.example.Questions.PetData;
import org.example.Task.GetPet;
import org.example.Task.PutPet;
import org.example.Task.DeletePet;

public class PetstoreStepDefinitions {

    private Actor usuario;

    @Before
    public void prepararEscenario() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("que el usuario configura la API de Petstore")
    public void configurarAPI() {
        usuario = OnStage.theActorCalled("Tester");
        usuario.whoCan(CallAnApi.at(
                "https://petstore.swagger.io/v2"
        ));
    }

    @Given("existe una mascota registrada")
    public void existeMascota() {

        long id = System.currentTimeMillis();
        String nombre = "Firulais";

        usuario.remember("mascotaId", id);
        usuario.remember("mascotaNombre", nombre);

        usuario.attemptsTo(
                PostPet.conDatos(id, nombre)
        );
    }

    @When("registra una mascota con datos validos")
    public void registrarMascota() {
        long id = System.currentTimeMillis();
        String nombre = "Firulais";

        usuario.remember("mascotaId", id);
        usuario.remember("mascotaNombre", nombre);

        usuario.attemptsTo(
                PostPet.conDatos(id, nombre)
        );
    }

    @When("consulta la mascota por su ID")
    public void consultarMascota() {
        Long id = usuario.recall("mascotaId");

        usuario.attemptsTo(
                GetPet.conId(id)
        );
    }

    @When("actualiza el nombre de la mascota")
    public void actualizarMascota() {
        Long id = usuario.recall("mascotaId");
        String nuevoNombre = "Firulais Actualizado";

        usuario.remember("nombreActualizado", nuevoNombre);

        usuario.attemptsTo(
                PutPet.conDatos(id,nuevoNombre)
        );
    }

    @When("consulta una mascota con un ID inexistente")
    public void consultarMascotaInexistente() {
        long idInexistente = 999999999999999L;

        usuario.attemptsTo(
                GetPet.conId(idInexistente)
        );
    }

    @Then("la respuesta debe tener codigo {int}")
    public void validarCodigo(int codigo) {
        usuario.should(
                seeThat(
                        "El codigo de respuesta HTTP",
                        ResponseCode.getStatus(),
                        equalTo(codigo)
                )
        );
    }

    @And("los datos de la mascota deben ser correctos")
    public void validarDatosMascota() {
        Long idEsperado = usuario.recall("mascotaId");
        String nombreEsperado = usuario.recall("mascotaNombre");

        usuario.should(
                seeThat("El ID de la mascota",
                        PetData.id(), equalTo(idEsperado)),
                seeThat("El nombre de la mascota",
                        PetData.nombre(), equalTo(nombreEsperado))
        );
    }

    @And("el ID consultado debe coincidir")
    public void validarIdMascota() {
        long idEsperado = usuario.recall("mascotaId");

        usuario.should(
                seeThat(
                        "El ID consultado",
                        PetData.id(),
                        equalTo(idEsperado)
                )
        );
    }

    @And("el nombre actualizado debe coincidir")
    public void validarNombreMascota() {
        String nombreEsperado =
                usuario.recall("nombreActualizado");

        usuario.should(
                seeThat(
                        "El nombre actualizado de la mascota",
                        PetData.nombre(),
                        equalTo(nombreEsperado)
                )
        );
    }

    @When("consulta nuevamente la mascota actualizada")
    public void consultarNuevamenteMascotaActualizada() {

        Long id = usuario.recall("mascotaId");

        usuario.attemptsTo(
                GetPet.conId(id)
        );
    }


    @When("elimina la mascota por su ID")
    public void eliminarMascota() {

        Long id = usuario.recall("mascotaId");

        usuario.attemptsTo(
                DeletePet.conId(id)
        );
    }

    @When("consulta la mascota eliminada")
    public void consultarMascotaEliminada() {

        Long id = usuario.recall("mascotaId");

        usuario.attemptsTo(
                GetPet.conId(id)
        );
    }


}
