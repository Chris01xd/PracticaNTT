
package org.example.Pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BuscarEmpleadoPage {

    public static final Target BTN_LISTA_EMPLEADOS =
            Target.the("Opcion Employee List")
                    .located(By.xpath(
                            "//a[normalize-space()='Employee List']"
                    ));

    public static final Target TXT_BUSCAR_EMPLEADO =
            Target.the("Campo de busqueda por nombre")
                    .located(By.xpath(
                            "//label[normalize-space()='Employee Name']" +
                                    "/ancestor::div[contains(@class,'oxd-input-group')]" +
                                    "//input"
                    ));

    public static final Target BTN_BUSCAR =
            Target.the("Boton Search")
                    .located(By.xpath(
                            "//button[@type='submit']"
                    ));

    public static final Target TABLA_RESULTADOS =
            Target.the("Tabla de resultados de empleados")
                    .located(By.cssSelector(
                            ".oxd-table-body"
                    ));

    public static final Target FILA_EMPLEADO =
            Target.the("Fila que contiene al empleado buscado")
                    .locatedBy(
                            "//div[contains(@class,'oxd-table-card')]" +
                                    "[contains(normalize-space(.),'{0}')]"
                    );

    public static final Target MENSAJE_SIN_RESULTADOS =
            Target.the("Mensaje de empleado no encontrado")
                    .located(By.xpath(
                            "//span[normalize-space()='No Records Found']"
                    ));

}
