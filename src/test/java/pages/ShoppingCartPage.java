package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class ShoppingCartPage extends BasePage {

    private final By checkout = By.id("checkout");
    private final By continueShopping = By.id("continue-shopping");

    @Override
    @Step("Esperando a que la pagina del carrito cargue")
    public void waitPageToLoad() {
        waitPage(checkout, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina del carrito")
    public void verifyPage() {
        Logs.info("Verificando la pagina del carrito");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShopping));
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkout));
    }

    @Step("Haciendo clic en el boton checkout")
    public void clickCheckoutButton(){
        Logs.info("Haciendo clic en el boton checkout");
        find(checkout).click();
    }

    @Step("Haciendo clic en el boton continue shopping")
    public void clickContinueShoppingButton(){
        Logs.info("Haciendo clic en el boton continue shopping");
        find(continueShopping).click();
    }

}