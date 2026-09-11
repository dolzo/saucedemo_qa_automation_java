package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class Footer extends BasePage {

    private final By twitterButton = By.cssSelector("a[data-test='social-x']");
    private final By facebookButton = By.cssSelector("a[data-test='social-facebook']");
    private final By linkedinButton = By.cssSelector("a[data-test='social-linkedin']");

    @Override
    @Step("Verificando el footer")
    public void verifyPage() {
        Logs.info("Verificando el footer");
        find(twitterButton).isDisplayed();
        find(facebookButton).isDisplayed();
        find(linkedinButton).isDisplayed();
    }

    // Depende de la pagina en donde uno se encuentra
    @Override
    public void waitPageToLoad() {}

    public boolean isTwitterDisplayed() {
        return find(twitterButton).isDisplayed();
    }

    public boolean isTwitterEnabled() {
        return find(twitterButton).isEnabled();
    }

    public String getTwitterUrl() {
        return find(twitterButton).getAttribute("href");
    }

    public boolean isFacebookDisplayed() {
        return find(facebookButton).isDisplayed();
    }

    public boolean isFacebookEnabled() {
        return find(facebookButton).isEnabled();
    }

    public String getFacebookUrl() {
        return find(facebookButton).getAttribute("href");
    }

    public boolean isLinkedinDisplayed() {
        return find(linkedinButton).isDisplayed();
    }

    public boolean isLinkedinEnabled() {
        return find(linkedinButton).isEnabled();
    }

    public String getLinkedinUrl() {
        return find(linkedinButton).getAttribute("href");
    }
}