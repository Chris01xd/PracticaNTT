
Feature: Inicio de sesion en OrangeHRM

  Como administrador del sistema
  Quiero iniciar sesion con mis credenciales
  Para acceder a la gestion de empleados

  @LoginPositivo
  Scenario Outline: Iniciar sesion con credenciales correctas
    Given que el usuario ingresa a OrangeHRM
    When inicia sesion con usuario "<usuario>" y clave "<clave>"
    Then visualiza el panel principal de OrangeHRM

    Examples:
      | usuario | clave    |
      | Admin   | admin123 |
