package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");


    @Override
    @Step("Esperando a que cargue la pagina de login")
    public void waitPageToLoad() {
        waitPage(usernameInput, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de login")
    public void verifyPage() {

        Logs.info("Verificando la pagina de login");
        softAssert.assertTrue(find(usernameInput).isDisplayed());
        softAssert.assertTrue(find(passwordInput).isDisplayed());
        softAssert.assertTrue(find(loginButton).isDisplayed());

        softAssert.assertAll();
    }

    @Step("Rellenando el formulario de login")
    public void fillLoginForm(String username, String password){
        Logs.info("Rellenando el input de nombre del usuario");
        find(usernameInput).sendKeys(username);

        Logs.info("Rellenando el input de password del usuario");
        find(passwordInput).sendKeys(password);

        Logs.info("Haciendo clic en el boton de login");
        find(loginButton).click();
    }

    @Step("Verificando el mensaje de error")
    public void verifyErrorMessage(String expectedMessage){
        Logs.info("Obteniendo el elemento del mensaje de error");
        final var errorLabel = find(errorMessage);

        Logs.info("Verificando el mensaje de error");
        softAssert.assertTrue(errorLabel.isDisplayed(), "El mensaje de error no se encuentra visible");

        softAssert.assertEquals(
                errorLabel.getText(),
                expectedMessage,
                "El mensaje de error no coincide con el esperado."
        );


        softAssert.assertAll();
    }
}
