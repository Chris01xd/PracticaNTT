
@Petstore
Feature: Gestion de mascotas en Swagger Petstore
  Como usuario de la API
  Quiero gestionar la informacion de mascotas
  Para verificar que las operaciones funcionen correctamente

  @positivo @post
  Scenario: Registrar una mascota correctamente
    Given que el usuario configura la API de Petstore
    When registra una mascota con datos validos
    Then la respuesta debe tener codigo 200
    And los datos de la mascota deben ser correctos

  @positivo @get
  Scenario: Consultar una mascota existente
    Given que el usuario configura la API de Petstore
    And existe una mascota registrada
    When consulta la mascota por su ID
    Then la respuesta debe tener codigo 200
    And el ID consultado debe coincidir


  @positivo @put
  Scenario: Actualizar una mascota existente
    Given que el usuario configura la API de Petstore
    And existe una mascota registrada
    When actualiza el nombre de la mascota
    Then la respuesta debe tener codigo 200
    And el nombre actualizado debe coincidir
    When consulta nuevamente la mascota actualizada
    Then la respuesta debe tener codigo 200
    And el nombre actualizado debe coincidir


  @positivo @delete
  Scenario: Eliminar una mascota existente
    Given que el usuario configura la API de Petstore
    And existe una mascota registrada
    When elimina la mascota por su ID
    Then la respuesta debe tener codigo 200
    When consulta la mascota eliminada
    Then la respuesta debe tener codigo 404


  @negativo @get
  Scenario: Consultar una mascota inexistente
    Given que el usuario configura la API de Petstore
    When consulta una mascota con un ID inexistente
    Then la respuesta debe tener codigo 404
