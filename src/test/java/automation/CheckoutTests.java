package automation;

import org.testng.annotations.BeforeMethod;
import pages.CheckoutPage;
import utilities.BaseTest;

public class CheckoutTests extends BaseTest {

    CheckoutPage checkoutPage = new CheckoutPage();

    @BeforeMethod
    public void setUp() {
        commonFlows.goToCheckoutPage();
    }

}
