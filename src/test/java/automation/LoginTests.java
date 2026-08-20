package automation;

import data.DataGiver;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);
    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToLoginPage();
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
        final var lockedCredentials = DataGiver.getLockedCredentials();
        loginPage.fillLoginForm(
                lockedCredentials.getUsername(),
                lockedCredentials.getPassword());

        loginPage.verifyErrorMessage(lockedCredentials.getMessage());

    }

    @Test(groups = {regression, smoke})
    @Description("Se verifica que aparezca un mensaje de error al hace login con un usuario que no se encuentre registrado")
    @Severity(SeverityLevel.CRITICAL)
    public void unexistentUserTest(){
        final var unexistentCredentials = DataGiver.getUnexistentCredentials();
        loginPage.fillLoginForm(
                unexistentCredentials.getUsername(),
                unexistentCredentials.getPassword()
        );
        loginPage.verifyErrorMessage(unexistentCredentials.getMessage());
    }
}
