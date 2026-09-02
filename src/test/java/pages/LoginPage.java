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
        waitPage(usernameInput, "Username Input");
        waitPage(passwordInput, "Password Input");
        waitPage(loginButton, "Login Button");
    }

    public boolean isUsernameInputDisplayed() {
        return find(usernameInput).isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        return find(passwordInput).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return find(loginButton).isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return find(errorMessage).isDisplayed();
    }

    public String getErrorMessageText() {
        return find(errorMessage).getText();
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
}