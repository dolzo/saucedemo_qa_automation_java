package utilities;

import com.github.javafaker.Faker;
import listeners.SuiteListeners;
import listeners.TestListeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {

    protected static final String regression = "regression";
    protected static final String smoke = "smoke";
    protected CommonFlows commonFlows = new CommonFlows();
    protected Faker faker;
    protected SoftAssert softAssert;
    private final DriverManager driverManager = new DriverManager();

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        faker = new Faker();
        softAssert = new SoftAssert();
        driverManager.buildDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        driverManager.killDriver();
    }
}