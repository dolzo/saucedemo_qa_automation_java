package automation;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BurgerMenu;
import pages.LoginPage;
import pages.ShoppingPage;
import pages.TopBar;
import utilities.BaseTest;
import utilities.Logs;

public class BurgerMenuTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final BurgerMenu burgerMenu = new BurgerMenu();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.openBurgerMenu();
    }

    @Test(groups = {smoke, regression})
    @Description("Se verifica el correcto funcionamiento del boton para cerrar sesion")
    @Severity(SeverityLevel.CRITICAL)
    public void logoutTest() {

        burgerMenu.logoutButtonClick();
        loginPage.waitPageToLoad();
        loginPage.verifyPage();

    }

    @Test(groups = {regression})
    @Description("Se verifica que el link del boton about sea correcto")
    @Severity(SeverityLevel.MINOR)
    public void verifyLinkAboutButton(){

        burgerMenu.verifyAboutButtonUrl("https://saucelabs.com/");

    }
}
