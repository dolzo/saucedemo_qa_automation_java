package automation;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;
import pages.YourInformationPage;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingCartTests extends BaseTest {

    private final ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    private final YourInformationPage yourInformationPage = new YourInformationPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToShoppingCartPage();
    }

    @Test(groups = {smoke})
    @Description("Verificar carga y elementos de la pagina de carrito")
    @Severity(SeverityLevel.NORMAL)
    public void verifyShoppingCartPageTest() {
        shoppingCartPage.verifyPage();

        softAssert.assertAll();
    }

    @Test(groups = {regression})
    @Description("Verificar navegacion al checkout desde el carrito")
    @Severity(SeverityLevel.CRITICAL)
    public void navigateToCheckoutTest() {
        shoppingCartPage.clickCheckoutButton();

        yourInformationPage.waitPageToLoad();
        yourInformationPage.verifyPage();

        softAssert.assertAll();
    }

    @Test(groups = {regression})
    @Description("Se verifica el funcionamiento del boton para volver a la pagina principal de shopping")
    @Severity(SeverityLevel.NORMAL)
    public void verifyContinueShoppingButtonTest() {
        shoppingCartPage.clickContinueShoppingButton();
    }
}