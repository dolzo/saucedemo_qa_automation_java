package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import utilities.BasePage;
import utilities.Logs;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingPage extends BasePage {

    private final By inventoryList = By.className("inventory_list");
    private final By productsTitle = By.cssSelector("span[data-test='title']");
    private final By selectItem = By.cssSelector("select[data-test='product-sort-container']");
    private final By itemNames = By.className("inventory_item_name");

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
        waitPage(inventoryList, "Inventory List");
        waitPage(productsTitle, "Products Title");
        waitPage(selectItem, "Select Item");
    }

    @Step("Yendo hacia los detalles de un producto")
    public void goToItemDetail(String itemName){
        Logs.info("Yendo hacia los detalles del producto %s", itemName);
        find(getItemName(itemName)).click();

    }

    @Step("Obteniendo el precio del producto: {itemName}")
    public String getItemPrice(String itemName){
        final var priceLocator = getProductPrice(itemName);
        return find(priceLocator).getText().replace("$", "").trim();
    }

    @Step("Seleccionando el ordenamiento por el valor {valueText}")
    public void selectSortOption(String valueText){
        Logs.info("Seleccionando el ordenamiento por el valor %s", valueText);
        Select sortDropdown = new Select(find(selectItem));
        sortDropdown.selectByValue(valueText);

    }

    @Step("Recuperando la lista de los productos")
    public List<String> getAllItemNames(){
        Logs.info("Recuperando la lista de los productos");
        List<WebElement> elements = findAll(itemNames);

        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}