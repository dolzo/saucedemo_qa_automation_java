package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.CredentialJson;
import models.ProductImageJson;
import utilities.Logs;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    private final static String credentialsPath = "src/test/resources/data/credenciales.json";
    private final static String productImagePath = "src/test/resources/data/productImages.json";

    public static CredentialJson getJsonCredentialsMap(){
        final var objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(
                    new File(credentialsPath),
                    CredentialJson.class
            );
        }catch (IOException ioException){
            Logs.error("Error al leer el Json de credenciales: %s", ioException.getLocalizedMessage());
            throw new RuntimeException(ioException.getLocalizedMessage());
        }
    }

    public static ProductImageJson getJsonProductImageMap(){
        final var objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(
                    new File(productImagePath),
                    ProductImageJson.class
            );
        }catch (IOException ioException){
            Logs.error("Error al leer el Json de imagenes de productos: %s", ioException.getLocalizedMessage());
            throw new RuntimeException(ioException.getLocalizedMessage());
        }
    }

}
