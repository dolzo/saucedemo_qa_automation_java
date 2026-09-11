package automation;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductDetailPage;
import pages.ShoppingPage;
import utilities.BaseTest;

public class ProductDetailTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final ProductDetailPage productDetailPage = new ProductDetailPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToProductDetail("Sauce Labs Fleece Jacket");
    }

    @Test(groups = {regression})
    @Description("Se verifican los elementos de la pagina de un producto")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductDetailTest() {

        productDetailPage.verifyPage();
    }

    @Test(groups = {regression, smoke})
    @Description("Se verifican el funcionamiento del boton 'Back to products'")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyBackToProductsButton() {

        productDetailPage.clickBackToProducts();
        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();

    }
}
