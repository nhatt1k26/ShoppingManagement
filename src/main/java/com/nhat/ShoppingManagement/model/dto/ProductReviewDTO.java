package com.nhat.ShoppingManagement.model.dto;

import com.nhat.ShoppingManagement.model.entity.Product;
import lombok.Data;
import lombok.Getter;

@Getter
public class ProductReviewDTO {
    private Integer star;
    private String review;
}
