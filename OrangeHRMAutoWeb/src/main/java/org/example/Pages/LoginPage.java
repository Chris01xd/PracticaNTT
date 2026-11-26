
package org.example.Pages;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target TXT_USUARIO =
            Target.the("Campo de usuario")
                    .located(By.name("username"));

    public static final Target TXT_CLAVE =
            Target.the("Campo de contraseña")
                    .located(By.name("password"));

    public static final Target BTN_LOGIN =
            Target.the("Boton de inicio de sesion")
                    .located(By.xpath("//button[@type='submit']"));

    public static final Target LBL_DASHBOARD =
            Target.the("Titulo del panel principal")
                    .located(By.xpath("//h6[normalize-space()='Dashboard']"));

    public static final Target MSG_ERROR =
            Target.the("Mensaje de credenciales incorrectas")
                    .located(By.xpath("//p[contains(@class,'oxd-alert-content-text')]"));
}
