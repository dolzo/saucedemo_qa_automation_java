package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.Select;
import utilities.BasePage;
import utilities.Logs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingPage extends BasePage {

    private final By inventoryList = By.className("inventory_list");
    private final By productsTitle = By.cssSelector("span[data-test='title']");
    private final By selectProduct = By.cssSelector("select[data-test='product-sort-container']");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By addToCartButton = By.className("btn_inventory");

    private By getProductPriceLocator(String productName){
        return RelativeLocator
                .with(By.className("inventory_item_price"))
                .below(getProductName(productName));

    }

    private By getProductName(String productName){
        final var xpath = String.format("//div[text()='%s']", productName);
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
        waitPage(selectProduct, "Select Product");
    }

    @Step("Yendo hacia los detalles de un producto")
    public void goToProductDetail(String productName){
        Logs.info("Yendo hacia los detalles del producto %s", productName);
        find(getProductName(productName)).click();

    }

    @Step("Obteniendo el precio del producto: {productName}")
    public String getProductPrice(String productName){
        final var priceLocator = getProductPriceLocator(productName);
        return find(priceLocator).getText().replace("$", "").trim();
    }

    @Step("Seleccionando el ordenamiento por el valor {valueText}")
    public void selectSortOption(String valueText){
        Logs.info("Seleccionando el ordenamiento por el valor %s", valueText);
        Select sortDropdown = new Select(find(selectProduct));
        sortDropdown.selectByValue(valueText);

    }

    @Step("Recuperando la lista de los productos")
    public List<String> getAllProductNames(){
        Logs.info("Recuperando la lista de los productos");
        List<WebElement> elements = findAll(productNames);

        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Recuperando la lista de los precios de los productos")
    public List<Double> getAllProductPrices(){
        Logs.info("Recuperando la lista de los precios de los productos");
        List<WebElement> elements = findAll(productPrices);
        List<Double> prices = new ArrayList<>();

        Logs.info("Remover el signo $ de los precios");
        for (WebElement element : elements) {
            final var price = Double.parseDouble(element.getText().replace("$", ""));
            prices.add(price);
        }

        return prices;
    }

    @Step("Haciendo clic en add to cart del primer producto")
    public void clickCartButton(){
        Logs.info("Haciendo clic en add to cart del primer producto");

        WebElement addToCart = find(addToCartButton);
        addToCart.click();

    }

    @Step("Haciendo clic en add to cart de todos los productos")
    public void clickAllAddToCart(){
        Logs.info("Haciendo clic en add to cart de todos los productos");

        List<WebElement> addToCartList = findAll(addToCartButton);

        for (WebElement element : addToCartList){
            element.click();
        }

    }

}