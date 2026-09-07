package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class CheckoutPage extends BasePage {


    private final By finishButton = By.id("finish");

    @Override
    @Step("Esperando a que la pagina de checkout cargue")
    public void waitPageToLoad() {
        waitPage(finishButton, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de checkout")
    public void verifyPage() {
        Logs.info("Verificando la pagina de checkout");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(finishButton));

    }

    @Step("Haciendo clic en el boton finalizar")
    public void clickFinishButton() {
        find(finishButton).click();
    }

}
