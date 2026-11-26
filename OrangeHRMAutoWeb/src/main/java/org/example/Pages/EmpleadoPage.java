
package org.example.Pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class EmpleadoPage {

    public static final Target BTN_PIM =
            Target.the("Modulo PIM")
                    .located(By.xpath("//span[normalize-space()='PIM']"));

    public static final Target BTN_AGREGAR =
            Target.the("Boton agregar empleado")
                    .located(By.xpath("//button[normalize-space()='Add']"));

    public static final Target TXT_NOMBRE =
            Target.the("Campo nombre del empleado")
                    .located(By.name("firstName"));

    public static final Target TXT_APELLIDO =
            Target.the("Campo apellido del empleado")
                    .located(By.name("lastName"));

    public static final Target TXT_ID_EMPLEADO =
            Target.the("Campo identificador del empleado")
                    .located(By.xpath(
                            "//label[normalize-space()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input"
                    ));
    public static final Target BTN_GUARDAR =
            Target.the("Boton guardar empleado")
                    .located(By.xpath("//button[@type='submit']"));

    public static final Target TITULO_DATOS_PERSONALES =
            Target.the("Titulo de datos personales")
                    .located(By.xpath("//h6[normalize-space()='Personal Details']"));

    public static final Target TXT_NOMBRE_REGISTRADO =
            Target.the("Nombre del empleado registrado")
                    .located(By.name("firstName"));

    public static final Target TXT_APELLIDO_REGISTRADO =
            Target.the("Apellido del empleado registrado")
                    .located(By.name("lastName"));
}
