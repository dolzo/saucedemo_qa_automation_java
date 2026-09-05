package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class TopBar extends BasePage {

    private final By title = By.className("app_logo");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By shoppingCart = By.cssSelector("a[data-test='shopping-cart-link']");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");
    private final By shoppingCartContainer = By.id("shopping_cart_container");

    // En blanco porque depende de la pagina en donde uno se encuentre
    @Override
    public void waitPageToLoad() {}

    @Override
    @Step("Verificando la barra superior")
    public void verifyPage() {
        Logs.info("Verificando la barra superior");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        wait.until(ExpectedConditions.visibilityOfElementLocated(burgerMenu));
    }

    @Step("Abriendo el menu hamburguesa")
    public void clickBurgerMenu(){
        Logs.info("Haciendo clic en el menu hamburguesa");
        find(burgerMenu).click();
    }

    @Step("Abriendo el carrito")
    public void clickCartButton(){
        Logs.info("Abriendo el carrito");
        find(shoppingCart).click();
    }

    @Step("Obteniendo la cantidad de productos en el carrito")
    public String getCartBadgeNumber(){
        Logs.info("Obteniendo la cantidad de productos en el carrito");
        return find(shoppingCartBadge).getText();
    }

    @Step("Revisando si el numero de items en el icono del carrito existe")
    public boolean cartBadgeNumberExist(){
        return !find(shoppingCartContainer).findElements(shoppingCartBadge).isEmpty();
    }
}