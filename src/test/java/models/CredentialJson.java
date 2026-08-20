package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CredentialJson {

    // Clase para modelar todo el json

    @JsonProperty("credentials")
    private Map<String, Credential> mapCredentials;

    public Map<String, Credential> getCredentialsMap() {
        return mapCredentials;
    }
}
