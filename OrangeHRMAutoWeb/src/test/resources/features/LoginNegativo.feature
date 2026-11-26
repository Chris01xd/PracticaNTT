
Feature: Validacion de inicio de sesion incorrecto en OrangeHRM

  Como usuario del sistema
  Quiero recibir un mensaje de error al ingresar credenciales incorrectas
  Para saber que no puedo acceder al sistema

  @LoginNegativo
  Scenario Outline: Rechazar inicio de sesion con credenciales incorrectas
    Given que el usuario ingresa a OrangeHRM
    When intenta iniciar sesion con usuario "<usuario>" y clave "<clave>"
    Then visualiza el mensaje de credenciales invalidas

    Examples:
      | usuario | clave      |
      | Admin   | clave123   |
      | usuario | admin123   |
