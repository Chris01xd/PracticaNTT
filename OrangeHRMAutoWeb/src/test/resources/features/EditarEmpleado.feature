
Feature: Edicion de empleados en OrangeHRM

  Como administrador del sistema
  Quiero modificar los datos de un empleado
  Para mantener actualizada su informacion

  @EditarEmpleado
  Scenario Outline: Editar el apellido de un empleado correctamente
    Given que el administrador inicia sesion en OrangeHRM
    When registra y modifica el apellido del empleado "<nombre>" "<apellido>" por "<nuevoApellido>"
    Then visualiza el apellido actualizado "<nuevoApellido>"

    Examples:
      | nombre | apellido | nuevoApellido |
      | Luis   | Perez    | Gonzalez      |
      | Maria  | Lopez    | Fernandez     |
