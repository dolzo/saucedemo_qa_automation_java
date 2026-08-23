package utilities;

import data.DataGiver;
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

        final var standardCredentials = DataGiver.getValidCredentials();

        Logs.info("Se hace login");
        new LoginPage().fillLoginForm(
                standardCredentials.getUsername(),
                standardCredentials.getPassword()
        );

        new ShoppingPage().waitPageToLoad(); // Se espera a que cargue la pagina de shopping
    }

    public void goToShoppingCartPage(){
        goToShoppingPage();

        new TopBar().clickCartButton();

        new ShoppingCartPage().waitPageToLoad();
    }

    public void goToYourInformationPage(){
        goToShoppingCartPage();

        new ShoppingCartPage().clickCheckoutButton();

        new YourInformationPage().waitPageToLoad();
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
