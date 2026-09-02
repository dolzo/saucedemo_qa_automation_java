package automation;

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

    @Test(groups = {regression, smoke})
    @Description("Se verifica el correcto funcionamiento de la opción para ordenar los items en base a su nombre")
    @Severity(SeverityLevel.NORMAL)
    public void itemListSortTest(){
        Logs.info("Seleccionar el ordenamiento de Z -> A");
        shoppingPage.selectSortOption("za");

        final var actualItemNames = shoppingPage.getAllItemNames();

        final var expectedProductNames = actualItemNames.stream()
                .sorted(Comparator.reverseOrder())
                .toList();


        softAssert.assertEquals(
                actualItemNames,
                expectedProductNames
        );

        softAssert.assertAll();

    }
}