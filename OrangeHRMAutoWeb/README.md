# Automatización Web – OrangeHRM

Proyecto de automatización de pruebas funcionales sobre el sitio de demostración de **OrangeHRM**

## 1. Objetivo

Validar el comportamiento de funcionalidades del sistema de gestión de empleados de OrangeHRM mediante **4 escenarios positivos y 2 escenarios negativos**, definidos con Gherkin y ejecutados de manera automatizada.

**Aplicación bajo prueba:** https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

> OrangeHRM es un entorno de demostración público. Su disponibilidad, velocidad y datos pueden cambiar entre ejecuciones.

## 2. Tecnologías

| Tecnología | Uso |
|---|---|
| Java | Desarrollo de la automatización (código compatible con Java 17) |
| Maven | Dependencias y ejecución de pruebas |
| Selenium WebDriver | Interacción con el navegador Chrome |
| Serenity BDD 4.0.3 | Implementación y reportes de pruebas |
| Cucumber | Ejecución de escenarios Gherkin |
| JUnit 4 | Runner de pruebas |
| Screenplay | Organización de actores, tareas, elementos y preguntas |

## 3. Escenarios automatizados

| N.º | Archivo `.feature` | Tipo | Casos (Examples) | Validación |
|---|---|---|---:|---|
| 1 | `Login.feature` | Positivo | 1 | Inicio de sesión y visualización del Dashboard |
| 2 | `RegistrarEmpleado.feature` | Positivo | 2 | Registro correcto de empleados en PIM |
| 3 | `BuscarEmpleado.feature` | Positivo | 2 | Búsqueda de empleados registrados |
| 4 | `EditarEmpleado.feature` | Positivo | 2 | Actualización y persistencia del apellido |
| 5 | `LoginNegativo.feature` | Negativo | 2 | Mensaje `Invalid credentials` |
| 6 | `BuscarEmpleadoNegativo.feature` | Negativo | 2 | Mensaje `No Records Found` |
| **Total** | **6 escenarios** | **4 positivos + 2 negativos** | **11** | |

Los escenarios emplean `Scenario Outline` y `Examples`. Las palabras clave de Gherkin están en inglés (`Feature`, `Scenario Outline`, `Given`, `When`, `Then`, `Examples`) y las descripciones de los pasos están en español.

Para reducir la dependencia entre pruebas, los escenarios de búsqueda y edición registran sus propios empleados, utilizando datos identificables y valores aleatorios cuando corresponde.

## 4. Estructura del proyecto

```text
AutomationExerciseAutoWeb/
├── pom.xml
├── README.md
├── evidencias/                       # Capturas generadas durante la ejecución
├── src/
│   ├── main/java/org/example/
│   │   ├── Pages/                    # Targets / localizadores
│   │   ├── Tasks/                    # Acciones Screenplay
│   │   ├── Questions/                # Validaciones Screenplay
│   │   └── Utils/
│   │       ├── Parameters.java
│   │       └── ScreenShot.java
│   └── test/
│       ├── java/org/example/
│       │   ├── RunnerTest.java
│       │   ├── StepDefinitions/      # Implementación de pasos Cucumber
│       │   └── Utils/
│       │       └── PreAndProsCondition.java  # Hooks, si se ubica aquí
│       └── resources/
│           ├── features/             # Los 6 archivos .feature
│           └── serenity.conf
└── target/                           # Resultados de Maven / Serenity
    └── site/serenity/index.html
```

> La ubicación de `PreAndProsCondition.java` debe coincidir con la configuración y las dependencias del proyecto. Los hooks necesitan estar incluidos en el `glue` de Cucumber.

## 5. Requisitos para ejecutar

- JDK **17 o superior** compatible con las dependencias del proyecto.
- **Maven** instalado y accesible desde la terminal.
- **Google Chrome** instalado.
- Conexión a Internet para acceder a OrangeHRM.

Verificar la instalación:

```powershell
java -version
mvn -version
```

## 6. Evidencias y reportes

Se implementan capturas de pantalla desde las **Step Definitions**, en momentos relevantes para cada escenario, mediante la utilidad `ScreenShot.captureScreenshot(...)`:

- **Login positivo:** pantalla de acceso y Dashboard validado.
- **Registro:** administrador autenticado y ficha del empleado registrado.
- **Búsqueda positiva:** empleado creado y resultado de búsqueda validado.
- **Edición:** datos anteriores, cambios guardados y apellido verificado después de recargar.
- **Login negativo:** pantalla de acceso y mensaje `Invalid credentials`.
- **Búsqueda negativa:** búsqueda realizada y mensaje `No Records Found`.

Las capturas personalizadas se guardan por escenario dentro de:

```text
evidencias/
```

El reporte principal de Serenity se consulta abriendo:

```text
target/site/serenity/index.html
```

**Nota:** las carpetas de evidencias se crean durante la ejecución cuando los hooks de Cucumber están correctamente configurados. Se recomienda verificar que los archivos `.png` se hayan generado antes de la entrega.

## 7. Diseño de automatización

El proyecto aplica **Screenplay**:

- **Actor:** representa al administrador que interactúa con OrangeHRM.
- **Pages:** contienen los elementos identificados mediante `Target`.
- **Tasks:** ejecutan acciones como iniciar sesión, registrar, buscar o editar.
- **Questions:** comprueban los resultados esperados.
- **StepDefinitions:** conectan los pasos de Gherkin con las Tasks y Questions, y registran las evidencias.
- **RunnerTest:** ejecuta los escenarios de Cucumber con Serenity.

Se utilizan esperas explícitas para interactuar con elementos cargados de forma dinámica.

## 8. Resultados y consideraciones

Durante el desarrollo se confirmó una ejecución conjunta de **7 de 7 casos positivos**, y se verificaron por separado los dos escenarios negativos. Posteriormente se informó que la suite completa de **11 casos** pasó después de reforzar la espera del campo `Employee Id`.

Las ejecuciones pueden verse afectadas por la latencia o disponibilidad del entorno público. Por esa razón, cada entrega debe incluir el **reporte y las evidencias de su ejecución final**.

## 9. Datos de demostración

Para las pruebas de inicio de sesión con usuario administrador se emplean las credenciales públicas del entorno demo:

```text
Usuario: Admin
Contraseña: admin123
```

Estas credenciales son exclusivas del ambiente público de demostración y no deben reutilizarse en otros sistemas.
