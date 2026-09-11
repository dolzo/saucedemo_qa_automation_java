package automation;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProductDetailPage;
import pages.TopBar;
import utilities.BaseTest;

public class TopBarTests extends BaseTest {

    private final TopBar topBar = new TopBar();
    private final ProductDetailPage productDetailPage = new ProductDetailPage();

    @BeforeMethod
    public void setUp() {
        commonFlows.goToItemDetail("Sauce Labs Fleece Jacket");
    }

    @Test(groups = {regression})
    @Description("Se verifica que al agregar un producto al carrito vacio, el numero sobre el icono de ese cambie a 1")
    @Severity(SeverityLevel.MINOR)
    public void verifyProductIcon() {
        productDetailPage.clickAddToCart();
        topBar.clickBurgerMenu();

        softAssert.assertEquals(
                topBar.getCartBadgeNumber(),
                "1"
        );

        softAssert.assertAll();
    }
}
