package automation;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ItemDetailPage;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class ItemDetailTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final ItemDetailPage itemDetailPage = new ItemDetailPage();

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
    @Description("Se verifican los elementos de la pagina de un producto")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyItemDetailTest() {
        shoppingPage.goToItemDetail("Sauce Labs Fleece Jacket");
        itemDetailPage.waitPageToLoad();

        itemDetailPage.verifyPage();
    }

    @Test(groups = {regression, smoke})
    @Description("Se verifican el funcionamiento del boton 'Back to products'")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyBackToProductsButton() {
        shoppingPage.goToItemDetail("Sauce Labs Fleece Jacket");
        itemDetailPage.waitPageToLoad();

        itemDetailPage.clickBackToProducts();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();

    }
}
