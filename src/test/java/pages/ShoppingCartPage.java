package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

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
        softAssert.assertTrue(find(continueShopping).isDisplayed());
        softAssert.assertTrue(find(checkout).isDisplayed());

        softAssert.assertAll();
    }

    @Step("Haciendo clic en el boton checkout")
    public void clickCheckoutButton(){
        Logs.info("Haciendo clic en el boton checkout");
        find(checkout).click();
    }
}
