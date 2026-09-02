package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class YourInformationPage extends BasePage {

    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessageLabel = By.cssSelector("h3[data-test='error']");

    @Override
    @Step("Esperando que la pagina your information cargue")
    public void waitPageToLoad() {
        waitPage(firstNameInput, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina your information")
    public void verifyPage() {
        Logs.info("Verificando la pagina your information");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInput));
    }

    @Step("Rellenar los campos del formulario de la pagina checkout")
    public void fillCheckoutInputs(String firstName, String lastName, String postalCode) {
        if (!firstName.isEmpty()) {
            Logs.info("Rellenando campo first name");
            find(firstNameInput).sendKeys(firstName);
        }

        if (!lastName.isEmpty()) {
            Logs.info("Rellenando campo last name");
            find(lastNameInput).sendKeys(lastName);
        }

        if (!postalCode.isEmpty()) {
            Logs.info("Rellenando campo zip code");
            find(postalCodeInput).sendKeys(postalCode);
        }

        Logs.info("Cliqueando el boton continue");
        find(continueButton).click();
    }

    @Step("Obteniendo el texto del mensaje de error")
    public String getErrorMessageText() {
        Logs.info("Obteniendo el texto del mensaje de error");
        return find(errorMessageLabel).getText();
    }

    @Step("Verificando si el mensaje de error esta visible")
    public boolean isErrorMessageDisplayed() {
        Logs.info("Verificando visibilidad del mensaje de error");
        return find(errorMessageLabel).isDisplayed();
    }
}