package pages;

import io.qameta.allure.Step;
import models.ProductItem;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilities.BasePage;
import utilities.Logs;

import java.util.List;

public class ShoppingPage extends BasePage {

    private final By inventoryList = By.className("inventory_list");
    private final By productsTitle = By.cssSelector("span[data-test='title']");
    private final By selectItem = By.cssSelector("select[data-test='product-sort-container']");

    private By getProductPrice(String itemName){
        return RelativeLocator
                .with(By.className("inventory_item_price"))
                .below(getItemName(itemName));

    }

    private By getItemName(String itemName){
        final var xpath = String.format("//div[text()='%s']", itemName);
        return By.xpath(xpath);
    }

    @Override
    @Step("Esperando a que cargue la pagina de shopping")
    public void waitPageToLoad() {
        waitPage(inventoryList, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de shopping")
    public void verifyPage() {

        Logs.info("Verificando la pagina de shopping");
        softAssert.assertTrue(find(inventoryList).isDisplayed());
        softAssert.assertTrue(find(productsTitle).isDisplayed());
        softAssert.assertTrue(find(selectItem).isDisplayed());

        softAssert.assertAll();
    }

    @Step("Yendo hacia los detalles de un producto")
    public void goToItemDetail(String itemName){
        Logs.info("Yendo hacia los detalles del producto %s", itemName);
        find(getItemName(itemName)).click();

    }

    @Step("Verificando el precio de los productos")
    public void verifyItemsPrice(List<ProductItem> itemList){
        Logs.info("Verificando el precio de los productos");

        for(var item: itemList){
            final var priceLocator = getProductPrice(item.getNombre());
            final var itemPrice = find(priceLocator).getText().replace("$", "").trim();

            softAssert.assertEquals(
                    itemPrice, // precio actual
                    item.getPrecio() // precio esperado
            );

        }

        softAssert.assertAll();
    }
}
