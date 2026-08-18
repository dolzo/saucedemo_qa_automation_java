package automation;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Footer;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class FooterTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final Footer footer = new Footer();

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
    @Description("Se verifica que la pagina a la que redirigen los enlaces sean las correctas")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLinkTest() {

        Logs.info("Se verifica que la pagina a la que redirigen los enlaces sean las correctas");
        footer.verifyPage();
        footer.verifyCorrectLinks(
                "https://twitter.com/saucelabs",
                "https://www.facebook.com/saucelabs",
                "https://www.linkedin.com/company/sauce-labs/");

    }
}
