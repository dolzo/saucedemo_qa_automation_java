package automation;

import data.CustomDataProviders;
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
        commonFlows.goToLoginPage();
    }

    @Test(groups = {regression})
    @Description("Se verifica la integridad de la pagina de login")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyLoginPage(){
        Logs.info("Se verifica la pagina de login");
        loginPage.verifyPage();
    }

    @Test(
            groups = {regression},
            dataProviderClass = CustomDataProviders.class,
            dataProvider = CustomDataProviders.DP_CREDENTIALS
    )
    @Description("Se verifica que aparezca un mensaje de error al hace login con un usuario que no es valido")
    @Severity(SeverityLevel.CRITICAL)
    public void credentialsTest(String username, String password, String message){
        loginPage.fillLoginForm(username, password);
        loginPage.verifyErrorMessage(message);
    }
}
