package data;

import models.User;
import org.testng.annotations.DataProvider;

public class CustomDataProviders {

    public static final String DP_CREDENTIALS = "dpCredentials";
    public static final String DP_MESSAGES = "dpMessages";

    @DataProvider(name = DP_CREDENTIALS)
    public Object[][] credentialsDataProvider(){
        final var locked = DataGiver.getLockedCredentials();
        final var unexistent = DataGiver.getUnexistentCredentials();

        return new Object[][]{
                {locked.getUsername(), locked.getPassword(), locked.getMessage()},
                {unexistent.getUsername(), unexistent.getPassword(), unexistent.getMessage()}
        };
    }

    @DataProvider(name = DP_MESSAGES)
    public Object[][] errorMessageDataProvider(){
        final var user = new User(); // rellenado con faker
        final var errorMessageMap = Parser.getErrorMessageMap(); // conseguido del excel

        return new Object[][]{
                {"", user.getLastName(), user.getPostalCode(), errorMessageMap.get("error_name").getMensaje()}, // sin el primer nombre
                {user.getName(), "", user.getPostalCode(), errorMessageMap.get("error_lastname").getMensaje()}, // sin el apellido
                {user.getName(), user.getLastName(), "", errorMessageMap.get("error_zipcode").getMensaje()} // sin el codigo postal
        };

    }
    
}
