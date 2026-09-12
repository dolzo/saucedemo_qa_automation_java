package automation;

import data.CustomDataProviders;
import data.ExcelReader;
import data.JsonReader;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShoppingPage;
import pages.TopBar;
import utilities.BaseTest;
import utilities.Logs;

import java.util.Comparator;

public class ShoppingTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final TopBar topBar = new TopBar();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        commonFlows.goToShoppingPage();
    }

    @Test(groups = {regression})
    @Description("Se verifican los elementos de la pagina de shopping")
    @Severity(SeverityLevel.BLOCKER)
    public void verifyPageTest() {
        Logs.info("Se verifican los elementos de la pagina de shopping");
        shoppingPage.verifyPage();
    }

    @Test(groups = {regression})
    @Description("Se verifica que los precios de los productos listados correspondan a los de la lista de Excel")
    @Severity(SeverityLevel.NORMAL)
    public void productListPriceTest(){
        final var productList = ExcelReader.readProductListExcel();

        for (var product : productList) {
            final var actualPrice = shoppingPage.getProductPrice(product.getNombre());
            softAssert.assertEquals(
                    actualPrice, // precio actual
                    product.getPrecio() // precio esperado
            );
        }

        softAssert.assertAll();
    }

    @Test(
            groups = {regression, smoke},
            dataProvider = CustomDataProviders.DP_SORT_VALUES_NAME,
            dataProviderClass = CustomDataProviders.class
    )
    @Description("Se verifica el correcto funcionamiento de la opción para ordenar los productos en base a su nombre")
    @Severity(SeverityLevel.NORMAL)
    public void productListSortNameTest(String sortOption, Comparator<String> comparator){
        Logs.info("Seleccionar el ordenamiento: %s", sortOption);
        shoppingPage.selectSortOption(sortOption);

        Logs.info("Recogiendo items actuales");
        final var actualProductNames = shoppingPage.getAllProductNames();

        Logs.info("Recogiendo items con el filtro de ordenado");
        final var expectedProductNames = actualProductNames.stream()
                .sorted(comparator)
                .toList();

        Logs.info("Validando el resultado");
        softAssert.assertEquals(
                actualProductNames,
                expectedProductNames
        );

        softAssert.assertAll();

    }

    @Test(
            groups = {regression, smoke},
            dataProvider = CustomDataProviders.DP_SORT_VALUES_PRICE,
            dataProviderClass = CustomDataProviders.class
    )
    @Description("Se verifica el correcto funcionamiento de la opción para ordenar los items en base a su precio")
    @Severity(SeverityLevel.NORMAL)
    public void productListSortPriceTest(String sortOption, Comparator<Double> comparator){
        Logs.info("Seleccionar el ordenamiento: %s", sortOption);
        shoppingPage.selectSortOption(sortOption);

        Logs.info("Recogiendo items actuales");
        final var actualProductPrices = shoppingPage.getAllProductPrices();

        Logs.info("Recogiendo items con el filtro de ordenado por precio");
        final var expectedProductPrices = actualProductPrices.stream()
                        .sorted(comparator)
                        .toList();

        Logs.info("Validando resultados");
        softAssert.assertEquals(
                actualProductPrices,
                expectedProductPrices
        );

        softAssert.assertAll();

    }

    @Test(groups = {regression})
    @Description("Se verifica que al agregar 6 productos al carrito vacio, el numero sobre el icono de este cambie a 6")
    @Severity(SeverityLevel.MINOR)
    public void verifySixProductIconTest(){
        shoppingPage.clickAllAddToCart();

        softAssert.assertEquals(
                topBar.getCartBadgeNumber(),
                "6"
        );

        softAssert.assertAll();

    }

    @Test(groups = {regression})
    @Description("Se verifica que al agregar un item al carrito despues eliminarlo de este, el contador desaparezca")
    @Severity(SeverityLevel.NORMAL)
    public void verifyRemovedProductIconTest(){
        // Se usa dos veces el metodo para hacer clic al boton porque el localizador no cambia
        shoppingPage.clickCartButton();
        shoppingPage.clickCartButton();

        softAssert.assertFalse(
                topBar.cartBadgeNumberExist()
        );

        softAssert.assertAll();
    }

    @Test(groups = {regression})
    @Description("Se verifica que las URLs de las imagenes de la pagina sean correctas")
    @Severity(SeverityLevel.MINOR)
    public void verifyImageUrlTest() {
        Logs.info("Cargando mapa esperado de imágenes desde el archivo JSON");
        final var expectedImageMap = JsonReader.getJsonProductImageMap().getProductImageMap();

        for (var entry : expectedImageMap.values()) {
            final var productName = entry.getName();
            final var expectedFullUrl = "https://www.saucedemo.com" + entry.getUrl();

            Logs.info("Verificando la URL de la imagen del producto: %s", productName);
            final var actualImageUrl = shoppingPage.getProductImageUrl(productName);

            softAssert.assertEquals(
                    actualImageUrl,
                    expectedFullUrl,
                    String.format("Fallo en la imagen del producto: '%s'", productName)
            );
        }

        softAssert.assertAll();
    }
}