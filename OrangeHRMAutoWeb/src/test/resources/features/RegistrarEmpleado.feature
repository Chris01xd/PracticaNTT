
Feature: Registro de empleados en OrangeHRM

  Como administrador del sistema
  Quiero registrar nuevos empleados
  Para gestionar la informacion del personal

  @RegistroEmpleado
  Scenario Outline: Registrar un nuevo empleado correctamente
    Given que el administrador inicia sesion en OrangeHRM
    When registra un empleado con nombre "<nombre>" y apellido "<apellido>"
    Then visualiza la informacion del empleado "<nombre>" "<apellido>"

    Examples:
      | nombre  | apellido |
      | Carlos  | Ramirez  |
      | Andrea  | Torres   |
