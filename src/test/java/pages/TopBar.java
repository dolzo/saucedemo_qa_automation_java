package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class TopBar extends BasePage {

    private final By title = By.className("app_logo");
    private final By burgerMenu = By.id("react-burger-menu-btn");

    // En blanco porque depende de la pagina en donde uno se encuentre
    @Override
    public void waitPageToLoad() {}

    @Override
    @Step("Verificando la barra superior")
    public void verifyPage() {
        Logs.info("Verificando la barra superior");
        softAssert.assertTrue(find(title).isDisplayed());
        softAssert.assertTrue(find(burgerMenu).isDisplayed());

        softAssert.assertAll();
    }

    @Step("Abriendo el menu hamburguesa")
    public void clickBurgerMenu(){
        Logs.info("Haciendo clic en el menu hamburguesa");
        find(burgerMenu).click();
    }

}
