package data;

import models.ErrorMessage;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static Map<String, ErrorMessage> getErrorMessageMap(){
        final var errorMessageMap = new HashMap<String,ErrorMessage>();

        final var errorMessageList = ExcelReader.readErrorMessageExcel();

        for(var errorMessage : errorMessageList){
            errorMessageMap.put(errorMessage.getNombre(), errorMessage);
        }

        return errorMessageMap;
    }

}
