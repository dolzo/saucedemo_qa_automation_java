package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class OrderConfirmationPage extends BasePage {

    private final By completeHeader = By.className("complete-header");
    private final By completeText = By.className("complete-text");
    private final By backHomeButton = By.id("back-to-products");

    @Override
    @Step("Esperando a que la pagina de confirmacion de orden cargue")
    public void waitPageToLoad() {
        waitPage(completeHeader, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de confirmacion de orden")
    public void verifyPage() {
        Logs.info("Verificando la pagina de confirmacion de orden");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader));
        wait.until(ExpectedConditions.visibilityOfElementLocated(completeText));
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton));
    }

    @Step("Obteniendo el titulo de confirmacion de orden")
    public String getCompleteTitle(){
        return find(completeHeader).getText();
    }

    @Step("Obteniendo el mensaje de confirmacion de orden")
    public String getCompleteMessage(){
        return find(completeText).getText();
    }

    @Step("Haciendo clic en el boton back to home")
    public void clickBackToHome(){
        find(backHomeButton).click();
    }
}
