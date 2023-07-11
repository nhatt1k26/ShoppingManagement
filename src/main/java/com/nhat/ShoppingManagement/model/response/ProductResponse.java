package com.nhat.ShoppingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhat.ShoppingManagement.model.entity.ProductReview;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String productName;
    private Float price;
    private String description;
    private Integer quantity;
    @JsonIgnoreProperties(value = {"product"})
    private List<ProductReview> productReviewList;
}


