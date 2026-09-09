package automation;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.ShoppingPage;
import utilities.BaseTest;

public class CheckoutTests extends BaseTest {

    private final CheckoutPage checkoutPage = new CheckoutPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();

    @BeforeMethod
    public void setUp() {
        commonFlows.goToCheckoutPage();
    }

    @Test(groups = {regression})
    @Description("Verificar que al cancelar una orden en el checkout se redirija a la pagina de shopping")
    @Severity(SeverityLevel.NORMAL)
    public void verifyCancelAtCheckoutTest() {
        checkoutPage.clickCancelButton();
        shoppingPage.verifyPage();
    }
}
