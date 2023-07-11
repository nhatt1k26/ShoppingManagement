package com.nhat.ShoppingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhat.ShoppingManagement.model.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ProductDataResponse {
    private Long id;
    private String name;
    private String type;
    private String url;
    private long size;
    @JsonIgnore
    private ProductResponse product;
    @JsonProperty(value = "productId")
    private Long getProductId(){
        return product.getId();
    }
}
