package com.nhat.ShoppingManagement.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Invoice invoice;
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Float unitCost;

    public InvoiceDetail( Product product, Integer quantity, Float unitCost) {
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }
}
