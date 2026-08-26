package utilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverManager {

    // para futuro uso en gherkin
    private final boolean runServer = System.getenv("JOB_NAME") != null;

    public void buildDriver(){
        // Esto es para ignorar las advertencias de nuevas versiones de selenium
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.OFF);

        if (runServer){
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    private void buildRemoteDriver(){

    }

    private void buildLocalDriver(){

        final var headlessMode = Boolean.parseBoolean(System.getProperty("headless", "false"));
        var browserProperty = System.getProperty("browser", "CHROME").toUpperCase();

        final Browser browser;

        // verificar si el navegador esta soportado
        try {
            browser = Browser.valueOf(browserProperty);
        }catch (IllegalArgumentException illegalArgumentException){
            throw new IllegalArgumentException(
                    String.format("El navegador indicado no está soportado: %s %s",
                            browserProperty,
                            illegalArgumentException.getLocalizedMessage())
            );
        }

        Logs.debug("Init webdriver: %s", browser);

        final var driver = switch (browser){
            case CHROME -> {
                // Opciones personalizadas chrome !!!
                Logs.debug("Creando opciones personalizadas para el Webdriver chrome");
                final Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("credentials_enable_service", false);
                chromePrefs.put("profile.password_manager_enabled", false);
                chromePrefs.put("profile.password_manager_leak_detection", false); // <======== This is the important one

                final var chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                chromeOptions.setAcceptInsecureCerts(true);

                if (headlessMode){
                    chromeOptions.addArguments("--headless=new");
                }

                yield new ChromeDriver(chromeOptions);
            }
            case FIREFOX -> {
                final var firefoxOptions = new FirefoxOptions();

                if (headlessMode){
                    firefoxOptions.addArguments("--headless");
                }

                yield new FirefoxDriver(firefoxOptions);
            }
            case EDGE -> {

                final var edgeOptions = new EdgeOptions();

                if (headlessMode){
                    edgeOptions.addArguments("--headless=new");
                }

                yield new EdgeDriver(edgeOptions);
            }
            case SAFARI -> new SafariDriver();
        };

        Logs.debug("Maximizando la pantalla del navegador");
        driver.manage().window().maximize();

        Logs.debug("Borrando cookies del navegador");
        driver.manage().deleteAllCookies();

        Logs.debug("Asignando driver al webdriver provider");
        new WebDriverProvider().set(driver);

    }

    public void killDriver(){
        Logs.debug("Matando el padre");
        var provider = new WebDriverProvider();
        var driver = provider.get();
        if (driver!=null){
            driver.quit();
        }
    }

    private enum Browser{
        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }
}
