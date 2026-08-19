package automation;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Footer;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class FooterTests extends BaseTest {

    private final Footer footer = new Footer();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToShoppingPage();
    }

    @Test(groups = {regression})
    @Description("Se verifica que la pagina a la que redirigen los enlaces sean las correctas")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLinkTest() {

        Logs.info("Se verifica que la pagina a la que redirigen los enlaces sean las correctas");
        footer.verifyPage();
        footer.verifyCorrectLinks(
                "https://twitter.com/saucelabs",
                "https://www.facebook.com/saucelabs",
                "https://www.linkedin.com/company/sauce-labs/");

    }
}
