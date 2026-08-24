package utilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverManager {

    // para uso en ghenkin
    private final boolean runServer = System.getenv("JOB_NAME") != null;

    public void buildDriver(){
        if (runServer){
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    private void buildRemoteDriver(){

    }

    private void buildLocalDriver(){
        Logs.debug("Creando opciones personalizadas para el Webdriver");
        final Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

        Logs.debug("Inicializando Selenium Webdriver con opciones personalizadas");
        final var driver = new ChromeDriver(chromeOptions);

        Logs.debug("Maximizando la pantalla del navegador");
        driver.manage().window().maximize();

        Logs.debug("Borrando cookies del navegador");
        driver.manage().deleteAllCookies();

        Logs.debug("Asignando driver al webdriver provider");
        new WebDriverProvider().set(driver);

        // Esto es para ignorar las advertencias de nuevas versiones de selenium
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.OFF);
    }

    public void killDriver(){
        Logs.debug("Matando el padre");
        new WebDriverProvider().get().quit();
    }
}
