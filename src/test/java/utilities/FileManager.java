package utilities;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    // Direccion donde se van a guardar las screenshots
    private final static String screenshotPath = "src/test/resources/screenshots";

    // Direccion donde se van a guardar las estructuras HTML de las paginas
    private final static String pageStructurePath = "src/test/resources/pageStructure";

    // Para guardar una screenshot
    public static void getScreenshot(String screenshotName){
        Logs.debug("Tomando captura de pantalla");
        // Logs.debug("Tomando screenshot con nombre \"%s\"", screenshotName);

        final var screenshotFile = ((TakesScreenshot) new WebDriverProvider().get())
                .getScreenshotAs(OutputType.FILE);

        // Se crea el path en base a la variable de la ruta y el nombre de la screenshot
        final var path = String.format("%s/%s.png", screenshotPath, screenshotName);

        // Try catch para guardar la screenshot
        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Ha ocurrido un error la tomar la captura de pantalla: %s", ioException.getLocalizedMessage());
        }
    }

    // Para tomar una screenshot de allure
    @Attachment(value = "failureScreenshot", type = "image/png")
    public static byte[] getScreenshot(){
        return ((TakesScreenshot) new WebDriverProvider().get())
                .getScreenshotAs(OutputType.BYTES);
    }

    // Para obtener la estructura HTML de la pagina en caso de que falle
    public static void getPageSource(String filename){

        Logs.debug("Tomando la estructura de la pagina");

        final var path = String.format("%s/page-source-%s.html", pageStructurePath, filename);

        // Try catch para guardar la estructura de la pagina
        try {
            final var file = new File(path);

            Logs.debug("Creando los directorios padres en el caso de que no existan");
            if (file.getParentFile().mkdirs()){
                final var fileWriter = new FileWriter(file);
                final var pageSource = new WebDriverProvider().get().getPageSource();
                fileWriter.write(Jsoup.parse(pageSource).toString());
                fileWriter.close();
            }

        }catch (IOException ioException){
            Logs.error("Error al obtener el page source: %s", ioException.getLocalizedMessage());
        }


    }

    // Para tomar el page structure en allure
    @Attachment(value = "pageSource", type = "text/html", fileExtension = "txt")
    public static String getPageSource(){
        return Jsoup.parse(new WebDriverProvider().get().getPageSource()).toString();
    }

    // Para borrar la carpeta de screenshots
    public static void deletePreviousEvidence(){
        try {
            Logs.debug("Borrando la evidencia anterior");
            FileUtils.deleteDirectory(new File(screenshotPath));
            FileUtils.deleteDirectory(new File(pageStructurePath));
        } catch (IOException ioException){
            Logs.error("Error al borrar la evidencia anterior: %s", ioException.getLocalizedMessage());
        }
    }


}
