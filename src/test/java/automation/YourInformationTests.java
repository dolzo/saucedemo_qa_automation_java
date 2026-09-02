package automation;

import data.CustomDataProviders;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.YourInformationPage;
import utilities.BaseTest;

public class YourInformationTests extends BaseTest {

    private YourInformationPage yourInformationPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToYourInformationPage();
        yourInformationPage = new YourInformationPage();
    }

    @Test(groups = {regression},
            dataProvider = CustomDataProviders.DP_MESSAGES,
            dataProviderClass = CustomDataProviders.class)
    @Description("Se verifica el funcionamiento de los mensajes de error al faltar un dato en la pagina checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void fillFormTest(
            String name,
            String lastName,
            String postalCode,
            String errorMessage
    ) {
        yourInformationPage.fillCheckoutInputs(name, lastName, postalCode);

        softAssert.assertTrue(yourInformationPage.isErrorMessageDisplayed(), "El mensaje de error no esta visible");
        softAssert.assertEquals(yourInformationPage.getErrorMessageText(), errorMessage, "El texto del mensaje de error no coincide");

        softAssert.assertAll();
    }
}