package utilities;

import com.github.javafaker.Faker;
import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


// Se llaman a los listeners para que automaticamente todos los tests los utilicen
@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {

    // protected Faker faker;

    protected static final String regression = "regression";
    protected static final String smoke = "smoke";
    protected CommonFlows commonFlows = new CommonFlows();
    protected Faker faker;
    private final DriverManager driverManager = new DriverManager();

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        faker = new Faker();
        driverManager.buildDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        driverManager.killDriver();
    }


}
