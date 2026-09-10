package automation;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;
import pages.YourInformationPage;
import utilities.BaseTest;

public class ShoppingCartTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToShoppingCartPage();
    }

    @Test(groups = {smoke})
    @Description("Verificar carga y elementos de la pagina de carrito")
    public void verifyShoppingCartPageTest() {
        final var shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.verifyPage();

        softAssert.assertAll();
    }

    @Test(groups = {regression})
    @Description("Verificar navegacion al checkout desde el carrito")
    public void navigateToCheckoutTest() {
        final var shoppingCartPage = new ShoppingCartPage();
        shoppingCartPage.clickCheckoutButton();

        final var yourInformationPage = new YourInformationPage();
        yourInformationPage.waitPageToLoad();
        yourInformationPage.verifyPage();

        softAssert.assertAll();
    }
}