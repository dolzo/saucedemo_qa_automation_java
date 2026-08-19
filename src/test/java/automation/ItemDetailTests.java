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

    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final ItemDetailPage itemDetailPage = new ItemDetailPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToItemDetail("Sauce Labs Fleece Jacket");
    }

    @Test(groups = {regression})
    @Description("Se verifican los elementos de la pagina de un producto")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyItemDetailTest() {

        itemDetailPage.verifyPage();
    }

    @Test(groups = {regression, smoke})
    @Description("Se verifican el funcionamiento del boton 'Back to products'")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyBackToProductsButton() {

        itemDetailPage.clickBackToProducts();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();

    }
}
