package data;

import org.testng.annotations.DataProvider;

public class CustomDataProviders {

    public static final String DP_CREDENTIALS = "dpCredentials";

    @DataProvider(name = DP_CREDENTIALS)
    public Object[][] credentialsDataProvider(){
        final var locked = DataGiver.getLockedCredentials();
        final var unexistent = DataGiver.getUnexistentCredentials();

        return new Object[][]{
                {locked.getUsername(), locked.getPassword(), locked.getMessage()},
                {unexistent.getUsername(), unexistent.getPassword(), unexistent.getMessage()}
        };
    }
    
}
