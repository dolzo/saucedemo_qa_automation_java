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

    protected SoftAssert softAssert;
    protected Faker faker;
    protected static final String regression = "regression";
    protected static final String smoke = "smoke";
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {
        softAssert = new SoftAssert();
        faker = new Faker();

        Logs.debug("Creando opciones personalizadas para el Webdriver");
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        Logs.debug("Inicializando Selenium Webdriver con opciones personalizadas");
        driver = new ChromeDriver(chromeOptions);

        Logs.debug("Maximizando la pantalla del navegador");
        driver.manage().window().maximize();

        Logs.debug("Borrando cookies del navegador");
        driver.manage().deleteAllCookies();

        // Logs.debug("Asignando un implicit wait de 5 segundos");
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Logs.debug("Asignando driver al webdriver provider");
        new WebDriverProvider().set(driver);

        // Esto es para ignorar las advertencias de nuevas versiones de selenium
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.OFF);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {

        Logs.debug("Cerrando Selenium Webdriver");
        driver.quit();

    }

    // ESTE METODO ES UNA MALA PRACTICA Y DEBE SER ERRADICADO --- USO SOLO PARA DEBUG
    protected void sleep(int timeMs){
        try {
            Thread.sleep(timeMs);
        }catch (InterruptedException interruptedException){
            Logs.error("InterruptedException: %s",
                    interruptedException.getLocalizedMessage());
        }
    }

}
