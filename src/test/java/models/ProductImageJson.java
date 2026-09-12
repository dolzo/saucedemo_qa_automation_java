package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ProductImageJson {

    // es para mapear la clave dinamica a objetos ProductImages

    @JsonProperty("images")
    private Map<String, ProductImage> productImageMap;

    public Map<String, ProductImage> getProductImageMap()
    {
        return productImageMap;
    }
}
