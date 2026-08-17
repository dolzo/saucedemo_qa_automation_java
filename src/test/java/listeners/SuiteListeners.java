package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import utilities.FileManager;
import utilities.Logs;

public class SuiteListeners implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        Logs.info("Suite iniciada: %s", suite.getName());

        // Se borra la carpeta de screenshots de la sesion anterior
        FileManager.deletePreviousEvidence();
    }

    @Override
    public void onFinish(ISuite suite) {
        Logs.info("Suite finalizada: %s", suite.getName());
    }
}
