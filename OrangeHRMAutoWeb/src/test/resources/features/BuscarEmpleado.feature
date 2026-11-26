
Feature: Busqueda de empleados en OrangeHRM

  Como administrador del sistema
  Quiero buscar empleados registrados
  Para consultar su informacion en el modulo PIM

  @BuscarEmpleado
  Scenario Outline: Buscar un empleado registrado correctamente
    Given que el administrador inicia sesion en OrangeHRM
    When registra y busca al empleado "<nombre>" "<apellido>"
    Then visualiza al empleado registrado en los resultados

    Examples:
      | nombre | apellido |
      | Carlos | Ramirez  |
      | Andrea | Torres   |
