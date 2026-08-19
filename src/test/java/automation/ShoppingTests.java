package automation;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToShoppingPage();
    }

    @Test(groups = {regression})
    @Description("Se verifican los elementos de la pagina de shopping")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyPageTest() {
        Logs.info("Se verifican los elementos de la pagina de shopping");
        shoppingPage.verifyPage();
    }
}
