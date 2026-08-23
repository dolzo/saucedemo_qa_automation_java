package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

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
        softAssert.assertTrue(find(firstNameInput).isDisplayed());
        softAssert.assertTrue(find(lastNameInput).isDisplayed());
        softAssert.assertTrue(find(postalCodeInput).isDisplayed());

        softAssert.assertAll();
    }

    @Step("Rellenar los campos del formulario de la pagina checkout")
    public void fillCheckoutInputs(String firstName, String lastName, String postalCode){

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

    @Step("Verificando el mensaje de error")
    public void verifyErrorMessage(String errorMessage){
        Logs.info("Verificando el mensaje de error");

        final var errorMessageElement = find(errorMessageLabel);

        softAssert.assertTrue(errorMessageElement.isDisplayed());
        softAssert.assertEquals(
                errorMessageElement.getText(),
                errorMessage);

        softAssert.assertAll();
    }

}