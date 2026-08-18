package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class Footer extends BasePage {

    private final By twitterButton = By.cssSelector("a[data-test='social-twitter']");
    private final By facebookButton = By.cssSelector("a[data-test='social-facebook']");
    private final By linkedinButton = By.cssSelector("a[data-test='social-linkedin']");

    @Override
    @Step("Verificando el footer")
    public void verifyPage() {
        Logs.info("Verificando el footer");
        softAssert.assertTrue(find(twitterButton).isDisplayed());
        softAssert.assertTrue(find(facebookButton).isDisplayed());
        softAssert.assertTrue(find(linkedinButton).isDisplayed());

        softAssert.assertAll();

    }

    // Depende de la pagina en donde uno se encuentra
    @Override
    public void waitPageToLoad() {}

    @Step("Verificando que los links del footer sean correctos")
    public void verifyCorrectLinks(
            String twitterUrl,
            String facebookUrl,
            String linkedinUrl
    ){

        final var twitterLabel = find(twitterButton);
        final var facebookLabel = find(facebookButton);
        final var linkedinLabel = find(linkedinButton);

        Logs.info("Verificando los links de redes sociales");

        softAssert.assertTrue(twitterLabel.isDisplayed());
        softAssert.assertTrue(twitterLabel.isEnabled());
        softAssert.assertEquals(twitterLabel.getAttribute("href"), twitterUrl);

        softAssert.assertTrue(facebookLabel.isDisplayed());
        softAssert.assertTrue(facebookLabel.isEnabled());
        softAssert.assertEquals(facebookLabel.getAttribute("href"), facebookUrl);

        softAssert.assertTrue(linkedinLabel.isDisplayed());
        softAssert.assertTrue(linkedinLabel.isEnabled());
        softAssert.assertEquals(linkedinLabel.getAttribute("href"), linkedinUrl);

        softAssert.assertAll();
    }
}
