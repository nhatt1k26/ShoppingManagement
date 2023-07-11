package com.nhat.ShoppingManagement.model.dto;

import lombok.Getter;

@Getter
public class ProductDTO {
    private String productName;
    private Float price;
    private String description;
    private Integer quantity;
}
