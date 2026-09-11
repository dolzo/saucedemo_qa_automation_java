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

        final var expectedTwitterUrl = "https://x.com/saucelabs";
        final var expectedFacebookUrl = "https://www.facebook.com/saucelabs";
        final var expectedLinkedinUrl = "https://www.linkedin.com/company/sauce-labs/";

        softAssert.assertTrue(footer.isTwitterDisplayed());
        softAssert.assertTrue(footer.isTwitterEnabled());
        softAssert.assertEquals(footer.getTwitterUrl(), expectedTwitterUrl);

        softAssert.assertTrue(footer.isFacebookDisplayed());
        softAssert.assertTrue(footer.isFacebookEnabled());
        softAssert.assertEquals(footer.getFacebookUrl(), expectedFacebookUrl);

        softAssert.assertTrue(footer.isLinkedinDisplayed());
        softAssert.assertTrue(footer.isLinkedinEnabled());
        softAssert.assertEquals(footer.getLinkedinUrl(), expectedLinkedinUrl);

        softAssert.assertAll();
    }
}