package automation;

import data.CustomDataProviders;
import data.ExcelReader;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

import java.util.Comparator;
import java.util.List;

public class ShoppingTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();

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
    @Description("Se verifica que los precios de los items listados correspondan a los de la lista de Excel")
    @Severity(SeverityLevel.NORMAL)
    public void itemListPriceTest(){
        final var itemList = ExcelReader.readProductListExcel();

        for (var item : itemList) {
            final var actualPrice = shoppingPage.getItemPrice(item.getNombre());
            softAssert.assertEquals(
                    actualPrice, // precio actual
                    item.getPrecio() // precio esperado
            );
        }

        softAssert.assertAll();
    }

    @Test(
            groups = {regression, smoke},
            dataProvider = CustomDataProviders.DP_SORT_VALUES_NAME,
            dataProviderClass = CustomDataProviders.class
    )
    @Description("Se verifica el correcto funcionamiento de la opción para ordenar los items en base a su nombre")
    @Severity(SeverityLevel.NORMAL)
    public void itemListSortNameTest(String sortOption, Comparator<String> comparator){
        Logs.info("Seleccionar el ordenamiento: %s", sortOption);
        shoppingPage.selectSortOption(sortOption);

        Logs.info("Recogiendo items actuales");
        final var actualItemNames = shoppingPage.getAllItemNames();

        Logs.info("Recogiendo items con el filtro de ordenado");
        final var expectedProductNames = actualItemNames.stream()
                .sorted(comparator)
                .toList();

        Logs.info("Validando el resultado");
        softAssert.assertEquals(
                actualItemNames,
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
    public void itemListSortPriceTest(String sortOption, Comparator<Double> comparator){
        Logs.info("Seleccionar el ordenamiento: %s", sortOption);
        shoppingPage.selectSortOption(sortOption);

        Logs.info("Recogiendo items actuales");
        final var actualItemPrices = shoppingPage.getAllItemPrices();

        Logs.info("Recogiendo items con el filtro de ordenado por precio");
        final var expectedItemPrices = actualItemPrices.stream()
                        .sorted(comparator)
                        .toList();

        Logs.info("Validando resultados");
        softAssert.assertEquals(
                actualItemPrices,
                expectedItemPrices
        );

        softAssert.assertAll();

    }
}