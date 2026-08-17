package listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import utilities.FileManager;
import utilities.Logs;
import utilities.WebDriverProvider;

public class AllureListeners implements TestLifecycleListener {

    @Override
    public void beforeTestStop(TestResult result) {
        Logs.debug("Before test stop de allure");

        final var status = result.getStatus();

        if (status == null) {
            return;
        }

        switch (status){
            case BROKEN, FAILED -> {
                // Si el driver no es nulo se obtienen las evidencias
                if (new WebDriverProvider().get() != null){
                    FileManager.getScreenshot();
                    FileManager.getPageSource();
                }
            }

        }
    }

}
