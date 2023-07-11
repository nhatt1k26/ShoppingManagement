package com.nhat.ShoppingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.security.models.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerCartResponse {
    private Long id;
    @JsonIgnore
    User user;
    @JsonIgnore
    Product product;
    private Integer itemQuantity;

    @JsonProperty(value="userId")
    private Long getUserId(){
        return user.getId();
    }

    @JsonProperty(value="productId")
    private Long getProductId(){
        return product.getId();
    }
}
