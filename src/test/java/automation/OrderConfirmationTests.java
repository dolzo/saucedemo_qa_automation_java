package automation;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.OrderConfirmationPage;
import utilities.BaseTest;

public class OrderConfirmationTests extends BaseTest {

    private final OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage();

    @BeforeMethod
    public void setUp() {
        commonFlows.goToOrderConfirmationPage();
    }

    @Test
    @Description("Se verifica que el flujo de compras funcione de manera satisfactoria")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyConfirmationTest() {
        softAssert.assertEquals(
                orderConfirmationPage.getCompleteTitle(),
                "Thank you for your order!"
        );
        softAssert.assertEquals(
                orderConfirmationPage.getCompleteMessage(),
                "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
        );

        softAssert.assertAll();
    }
}
