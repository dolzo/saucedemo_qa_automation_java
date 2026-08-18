package automation;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

public class LoginTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navegando a la pagina");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad(); // Se espera a que cargue la pagina
    }

    @Test(groups = {regression, smoke})
    @Description("Se verifica la integridad de la pagina de login")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyLoginPage(){
        Logs.info("Se verifica la pagina de login");
        loginPage.verifyPage();
    }

    @Test(groups = {regression, smoke})
    @Description("Se verifica que aparezca un mensaje de error al hace login con un usuario invalido")
    @Severity(SeverityLevel.CRITICAL)
    public void lockedOutUserTest() {

        loginPage.fillLoginForm("locked_out_user", "secret_sauce");
        loginPage.verifyErrorMessage("Epic sadface: Sorry, this user has been locked out.");

    }
}
