package data;

import models.Credential;

import java.util.Map;

public class DataGiver {

    private static Map<String, Credential> getCredentialsMap(){

        return JsonReader.getJsonCredentialsMap().getCredentialsMap();

    }

    public static Credential getValidCredentials(){
        return getCredentialsMap().get("valid");
    }

    public static Credential getLockedCredentials(){
        return getCredentialsMap().get("locked");
    }

    public static Credential getUnexistentCredentials(){
        return getCredentialsMap().get("unexistent");
    }

}
