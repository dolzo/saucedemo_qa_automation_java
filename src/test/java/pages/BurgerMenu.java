package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class BurgerMenu extends BasePage {

    private final By logoutButton = By.id("logout_sidebar_link");
    private final By aboutButton = By.id("about_sidebar_link");

    @Override
    @Step("Esperando a que el menu hamburguesa aparezca")
    public void waitPageToLoad() {
        waitPage(logoutButton, this.getClass().getSimpleName());

        Logs.info("Esperando a que el boton de logout sea clickeable");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(
                ExpectedConditions.elementToBeClickable(logoutButton)
        );

    }

    @Override
    @Step("Verificando el menu hamburguesa")
    public void verifyPage() {
        Logs.info("Verificando el menu hamburguesa");
        softAssert.assertTrue(find(logoutButton).isDisplayed());
        softAssert.assertTrue(find(aboutButton).isDisplayed());

        softAssert.assertAll();
    }

    @Step("Haciendo clic en el boton de logout")
    public void logoutButtonClick(){
        Logs.info("Haciendo clic en el boton de logout");
        find(logoutButton).click();
    }

    @Step("Verificando el boton about")
    public void verifyAboutButtonUrl(String expectedUrl){
        Logs.info("Verificando el boton about");
        softAssert.assertTrue(find(aboutButton).isDisplayed());
        softAssert.assertTrue(find(aboutButton).isEnabled());
        softAssert.assertEquals(find(aboutButton).getAttribute("href"), expectedUrl);

        softAssert.assertAll();

    }

}
