package utilities;

import org.openqa.selenium.WebDriver;
import pages.*;

public class CommonFlows {

    private WebDriver getDriver(){
        return new WebDriverProvider().get();
    }

    public void goToLoginPage(){
        Logs.info("Navegando a la pagina");
        getDriver().get("https://www.saucedemo.com/");

        new LoginPage().waitPageToLoad(); // Se espera a que cargue la pagina
    }

    public void goToShoppingPage(){
        goToLoginPage();

        Logs.info("Se hace login");
        new LoginPage().fillLoginForm("standard_user", "secret_sauce");

        new ShoppingPage().waitPageToLoad(); // Se espera a que cargue la pagina de shopping
    }

    public void openBurgerMenu(){
        goToShoppingPage();

        new TopBar().clickBurgerMenu();
        new BurgerMenu().waitPageToLoad();
    }

    public void goToItemDetail(String itemName){
        goToShoppingPage();

        new ShoppingPage().goToItemDetail(itemName);
        new ItemDetailPage().waitPageToLoad();
    }
}
