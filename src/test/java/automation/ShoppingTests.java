package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navegando a la pagina");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad(); // Se espera a que cargue la pagina de login

        Logs.info("Se hace login");
        loginPage.fillLoginForm("standard_user", "secret_sauce");

        shoppingPage.waitPageToLoad(); // Se espera a que cargue la pagina de shopping
    }

    @Test(groups = {regression})
    public void verifyPageTest() {
        Logs.info("Se verifican los elementos de la pagina de shopping");
        shoppingPage.verifyPage();
    }
}
