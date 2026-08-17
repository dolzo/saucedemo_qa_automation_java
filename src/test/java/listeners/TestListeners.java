package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.FileManager;
import utilities.Logs;

public class TestListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Logs.info("Comenzando el test: %s", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Logs.info("Test exitoso: %s", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Logs.info("Test Fallido: %s", result.getName());

        // Tomamos una captura de pantalla cuando el metodo falle
        FileManager.getScreenshot(result.getName());

        // Si es que el metodo falla se guarda el page source!
        FileManager.getPageSource(result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Logs.info("Test omitido: %s", result.getName());
    }
}
