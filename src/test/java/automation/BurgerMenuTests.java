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
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final TopBar topBar = new TopBar();
    private final BurgerMenu burgerMenu = new BurgerMenu();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navegando a la pagina");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad(); // Se espera a que cargue la pagina de login

        Logs.info("Se hace login");
        loginPage.fillLoginForm("standard_user", "secret_sauce");

        shoppingPage.waitPageToLoad(); // Se espera a que cargue la pagina de shopping

        topBar.clickBurgerMenu();
        burgerMenu.waitPageToLoad();
    }

    @Test(groups = {smoke, regression})
    @Description("Se verifica el correcto funcionamiento del boton para cerrar sesion")
    @Severity(SeverityLevel.CRITICAL)
    public void logoutTest() {

        burgerMenu.logoutButtonClick();
        loginPage.waitPageToLoad();
        loginPage.verifyPage();

    }
}
