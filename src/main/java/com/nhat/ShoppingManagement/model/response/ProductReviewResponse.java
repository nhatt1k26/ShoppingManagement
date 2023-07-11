package com.nhat.ShoppingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.security.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewResponse {
    private Long id;

    @JsonIgnore
    private User user;
    @JsonIgnore
    private Product product;
    @JsonProperty(value = "productId",defaultValue = "null")
    private Long getProductId(){
        return product.getId();
    }

    @JsonProperty("userId")
    private Long getUserId(){
        return user.getId();
    }
    private Integer star;
    private String review;
}
