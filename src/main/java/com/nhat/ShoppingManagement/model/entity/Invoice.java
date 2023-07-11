package com.nhat.ShoppingManagement.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhat.ShoppingManagement.security.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
//@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    User user;
    @OneToMany(fetch = FetchType.EAGER,orphanRemoval = true,mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetail;
    private Double totalCost;
    private LocalDateTime generateAt;

    public Invoice() {
        this.generateAt = LocalDateTime.now();
    }
    public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
        this.invoiceDetail = invoiceDetail;
        this.totalCost = invoiceDetail.stream().mapToDouble(a -> a.getUnitCost()*a.getQuantity()).sum();
    }
}
