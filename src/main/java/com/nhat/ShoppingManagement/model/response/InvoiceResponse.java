package com.nhat.ShoppingManagement.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhat.ShoppingManagement.model.entity.InvoiceDetail;
import com.nhat.ShoppingManagement.security.models.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
public class InvoiceResponse {
    private Long id;
    @JsonIgnore
    User user;

    @JsonProperty("userId")
    private Long getUserId(){
        return user.getId();
    }

    @JsonIgnoreProperties(value ={"invoice","id","product.description"})
    private List<InvoiceDetail> invoiceDetail;

    private Double totalCost;
    private LocalDateTime localDateTime;


}
