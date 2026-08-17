package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    private final static int defaultTimeout = 5;
    private final int timeOut;
    protected final SoftAssert softAssert;

    // Constructo al que se le pasa un timeout personalizado
    public BasePage(int timeOut) {
        softAssert = new SoftAssert();
        this.timeOut = timeOut;
    }

    // Constructor con el timeout por defecto
    public BasePage() {
        this(defaultTimeout); // Se llama al constructor de arriba con el timeout por defecto de valor 5
    }

    // Se obtiene el driver de una manera mas limpia
    protected WebDriver getDriver() {
        return new WebDriverProvider().get();
    }

    // Encapsular la espera de una pagina
    protected void waitPage(By locator, String pageName) {
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(this.timeOut));

        Logs.info("Esperando a que la pagina %s cargue", pageName);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        Logs.info("%s ha cargado de manera exitosa", pageName);

    }

    // Metodo para retornar un elemento de manera mas limpia
    protected WebElement find(By locator){
        return getDriver().findElement(locator);
    }

    // Metodo para retornar muchos elementos de manera mas limpia
    protected List<WebElement> findAll(By locator){
        return getDriver().findElements(locator);
    }

    // Se fuerza a que se espere que cargue la pagina
    public abstract void waitPageToLoad();

    // Se fuerza a que se verifique la UI de cada pagina
    public abstract void verifyPage();
}
