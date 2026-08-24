package utilities;

import data.DataGiver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.*;

public class CommonFlows {

    private WebDriver getDriver(){
        return new WebDriverProvider().get();
    }

    private void assignLoginCookie(){
        Logs.debug("Asignando cookie de login");
        getDriver().get("https://www.saucedemo.com/404"); // esta pagina la uso solo para asignar la cookie
        final var credencialesValidas = DataGiver.getValidCredentials();
        final var loginCookie =
                new Cookie("session-username", credencialesValidas.getUsername());
        getDriver().manage().addCookie(loginCookie);
    }

    public void goToLoginPage(){
        Logs.info("Navegando a la pagina");
        getDriver().get("https://www.saucedemo.com/");

        new LoginPage().waitPageToLoad(); // Se espera a que cargue la pagina
    }

    public void goToShoppingPage(){
        assignLoginCookie();
        getDriver().get("https://www.saucedemo.com/inventory.html");

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
