# PetstoreAutoAPI — Automatización de pruebas API

Proyecto de automatización de pruebas de la API pública **Swagger Petstore** utilizando Serenity BDD, Cucumber y el patrón Screenplay.

## Objetivo

Validar las operaciones de registro, consulta, actualización y eliminación de mascotas mediante peticiones HTTP y aserciones sobre los códigos de respuesta y los datos JSON. También se comprueba que los cambios realizados con PUT y DELETE se reflejen en consultas GET posteriores.

## API utilizada

- **Base URL:** https://petstore.swagger.io/v2
- **POST `/pet`:** registrar una mascota.
- **GET `/pet/{petId}`:** consultar una mascota por su ID.
- **PUT `/pet`:** actualizar los datos de una mascota.
- **DELETE `/pet/{petId}`:** eliminar una mascota por su ID.

Se prueban **dos rutas de API** (`/pet` y `/pet/{petId}`) y **cuatro verbos HTTP** (POST, GET, PUT y DELETE).

## Tecnologías

- Java (código fuente configurado para Java 17)
- Maven
- Serenity BDD 3.1.20
- Cucumber con Gherkin
- JUnit 4
- Serenity Screenplay REST / REST Assured
- IntelliJ IDEA (entorno de desarrollo)

## Estructura del proyecto

```text
PetstoreAutoAPI/
├── .gitignore
├── pom.xml
├── README.md
└── src/
    ├── main/java/org/example/
    │   ├── Questions/
    │   │   ├── PetData.java
    │   │   └── ResponseCode.java
    │   └── Task/
    │       ├── DeletePet.java
    │       ├── GetPet.java
    │       ├── PostPet.java
    │       └── PutPet.java
    └── test/
        ├── java/org/example/
        │   ├── RunnerTest.java
        │   └── StepDefinitions/
        │       └── PetstoreStepDefinitions.java
        └── resources/features/
            └── Petstore.feature
```

- **`features/`:** escenarios escritos en Gherkin.
- **`StepDefinitions/`:** conexión entre los pasos Gherkin y el código Java.
- **`Task/`:** acciones HTTP realizadas por el actor Screenplay.
- **`Questions/`:** consultas y validaciones de las respuestas de la API.
- **`RunnerTest.java`:** ejecutor de los escenarios con Serenity y Cucumber.
- **`pom.xml`:** dependencias y configuración de Maven.

## Escenarios automatizados

| N.º | Tipo | Escenario | Validación principal |
| --- | --- | --- | --- |
| 1 | Positivo | Registrar una mascota correctamente | HTTP 200 e ID/nombre esperados. |
| 2 | Positivo | Consultar una mascota existente | HTTP 200 e ID consultado correcto. |
| 3 | Positivo | Actualizar una mascota existente | HTTP 200, nombre actualizado y **GET posterior** para confirmar que el cambio quedó guardado. |
| 4 | Positivo | Eliminar una mascota existente | HTTP 200 al eliminar y **GET posterior con HTTP 404** para confirmar que ya no existe. |
| 5 | Negativo | Consultar una mascota inexistente | HTTP 404 para un ID no registrado. |

Cada escenario prepara sus datos cuando es necesario, evitando depender del resultado de otros escenarios. Se usa un identificador basado en `System.currentTimeMillis()` para las mascotas creadas durante las pruebas.

## Requisitos previos

1. Tener instalado un **JDK compatible** (el proyecto compila con nivel de lenguaje Java 17).
2. Tener **Apache Maven** instalado y disponible desde la terminal.
3. Contar con conexión a Internet para consumir la API pública.

Para verificar el entorno:

```bash
java -version
mvn -version
```

## Ejecución

Desde la carpeta raíz del proyecto, ejecutar:

```bash
mvn clean verify
```

Este comando compila el proyecto, ejecuta los escenarios mediante Maven Failsafe y genera el reporte Serenity.

> **Compatibilidad:** El `pom.xml` incluye en Maven Failsafe la opción `--add-opens=java.base/java.lang=ALL-UNNAMED` para permitir el acceso por reflexión requerido por las dependencias utilizadas al ejecutar los tests en JDK modernos.

### Resultado obtenido en la última ejecución

```text
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
SERENITY TESTS: SUCCESS
BUILD SUCCESS
```

## Reporte Serenity

Después de ejecutar las pruebas, abrir en el navegador:

```text
target/site/serenity/index.html
```

El reporte permite visualizar los escenarios, sus pasos y el resultado de cada prueba. La carpeta `target/` se genera automáticamente al ejecutar Maven y no necesita incluirse en el repositorio.

## Decisión de diseño: patrón Screenplay

Se eligió el patrón **Screenplay** para separar las responsabilidades del proyecto:

- El **actor** configura el acceso a la API y ejecuta las acciones.
- Las clases **Task** encapsulan las solicitudes POST, GET, PUT y DELETE, permitiendo reutilizarlas.
- Las clases **Question** obtienen el código HTTP y los datos de la respuesta.
- Las **Step Definitions** conectan los escenarios Gherkin con las tareas y validaciones.

En el escenario de actualización, se consulta nuevamente la mascota mediante GET después del PUT. Esto verifica no solo la respuesta de actualización, sino también que el nuevo nombre pueda recuperarse en una solicitud posterior.

En el escenario de eliminación, se consulta mediante GET el mismo ID después de DELETE, esperando **HTTP 404** para comprobar que la mascota ya no está disponible.


