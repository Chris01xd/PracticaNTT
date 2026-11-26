
package org.example.Questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static org.example.Pages.BuscarEmpleadoPage.FILA_EMPLEADO;

public class EmpleadoEncontrado implements Question<Boolean> {

    private final String nombre;

    public EmpleadoEncontrado(String nombre) {
        this.nombre = nombre;
    }

    public static EmpleadoEncontrado conNombre(String nombre) {
        return new EmpleadoEncontrado(nombre);
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        return FILA_EMPLEADO.of(nombre)
                .resolveAllFor(actor)
                .stream()
                .anyMatch(elemento -> elemento.isVisible());
    }
}
