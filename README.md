# SauceDemo QA Automation Framework (Java + Selenium)

Framework de automatización de pruebas End-to-End (E2E) para la plataforma web [SauceDemo](https://www.saucedemo.com/), desarrollado en Java con Selenium WebDriver, TestNG y estructurado bajo el patrón Page Object Model (POM).

---

## Prerrequisitos

### Contar con los siguientes elementos instalados en el entorno local:

- Java Development Kit (JDK 17+).
- Navegador actualizado (Chrome).
- IDE recomendado: IntelliJ IDEA.

Nota para Linux/macOS: Si es necesario, otorgar permisos de ejecución a los scripts y al wrapper: 
```bash
chmod +x mvnw *.sh
```

### Clonación y configuración

1. Clonar el repositorio

```bash
git clone https://github.com/dolzo/saucedemo_qa_automation_java
```

2. Entrar a la carpeta del repositorio clonado

```bash
cd saucedemo_qa_automation_java/
```

## Ejecución de pruebas

El proyecto cuenta con archivos de scripts para la rápida realización de pruebas.

1. runSuite.sh
  ```bash
  ./runSuite.sh
  ```
  Este script se encarga de correr todos los tests de la suite.

2. runRegression.sh
  ```bash
  ./runRegression.sh
  ```
  Este script se encarga de correr los tests que pertenezcan al grupo 'regression'.

Si se desea, se pueden ejecutar pruebas directamente desde la terminal.

1. Ejecutar todos los tests de la suite.
```bash
./mvnw clean test
```
   

2. Ejecutar todos los tests del grupo de pruebas de regresión.
```bash
./mvnw clean test -Dgroups="regression"
```

3. Ejecutar todos los tests del grupo de pruebas de humo.
```bash
./mvnw clean test -Dgroups="smoke"
```

## Reportes de ejecución

**Generar y abrir reportes de allure:** 
```bash
./mvnw allure:serve
```
o en su defecto
```bash
./openAllure.sh
```

**Capturas de pantalla ante fallos:** Guardadas de manera automática en `src/test/resources/screenshots`. Se incluyen en el reporte de allure.

**Estructura de la página ante fallos:** Guardada de manera automática en `src/test/resources/pageStructure`. Se incluyen en el reporte de allure. Es utilizada para ayudar en la depuración.

## Buenas prácticas aplicadas

**Aislamiento de pruebas:** Cada test es independiente de los demás, no teniendo que depender de otros para su ejecución.

**Esperas explícitas:** Se ha hecho foco en el uso de `WebDriverWait` con `ExpectedConditions` por sobre las esperas estáticas (`Thread.sleep()`).

**Page Object Model:** Uso del patrón de diseño Page Object Model (POM) para el desarrollo de las pruebas dentro del framework.
