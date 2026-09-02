package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class ItemDetailPage extends BasePage {

    private final By itemName = By.className("inventory_details_name");
    private final By itemDescription = By.className("inventory_details_desc");
    private final By itemPrice = By.className("inventory_details_price");
    private final By itemImage = By.className("inventory_details_img");
    private final By addToCartButton = By.id("add-to-cart");
    private final By backToProductsButton = By.id("back-to-products");

    @Override
    @Step("Esperando a que el detalle del producto cargue")
    public void waitPageToLoad() {
        waitPage(itemDescription, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando los detalles del producto")
    public void verifyPage() {
        Logs.info("Verificando los detalles del producto");

        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemDescription));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice));
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemImage));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(backToProductsButton));
    }

    @Step("Haciendo clic en back to products")
    public void clickBackToProducts(){
        Logs.info("Haciendo clic en back to products");
        find(backToProductsButton).click();
    }
}