
Feature: Busqueda de empleados inexistentes en OrangeHRM

  Como administrador del sistema
  Quiero identificar cuando un empleado no existe
  Para evitar consultar informacion incorrecta

  @BuscarEmpleadoNegativo
  Scenario Outline: Buscar un empleado que no existe
    Given que el administrador inicia sesion en OrangeHRM
    When busca un empleado inexistente con nombre "<nombre>"
    Then visualiza el mensaje de que no existen registros

    Examples:
      | nombre       |
      | EmpleadoXYZ  |
      | PersonaABC   |
