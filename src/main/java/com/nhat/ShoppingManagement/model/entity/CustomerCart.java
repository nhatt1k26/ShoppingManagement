package com.nhat.ShoppingManagement.model.entity;

import com.nhat.ShoppingManagement.security.models.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;
    @ManyToOne
    Product product;
    private Integer itemQuantity;

    public CustomerCart(User user, Product product, Integer itemQuantity) {
        this.user = user;
        this.product = product;
        this.itemQuantity = itemQuantity;
    }
}
