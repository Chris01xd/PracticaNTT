
package org.example.Pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class EditarEmpleadoPage {

    public static final Target TXT_APELLIDO =
            Target.the("Campo apellido del empleado")
                    .located(By.name("lastName"));

    public static final Target BTN_GUARDAR_CAMBIOS =
            Target.the("Boton guardar cambios personales")
                    .located(By.xpath(
                            "//h6[normalize-space()='Personal Details']" +
                                    "/ancestor::div[contains(@class,'orangehrm-card-container')]" +
                                    "//button[@type='submit']"
                    ));

    public static final Target TITULO_DATOS_PERSONALES =
            Target.the("Titulo de datos personales")
                    .located(By.xpath(
                            "//h6[normalize-space()='Personal Details']"
                    ));
}
