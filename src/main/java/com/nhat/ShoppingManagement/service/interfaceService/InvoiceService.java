package com.nhat.ShoppingManagement.service.interfaceService;

import com.nhat.ShoppingManagement.model.entity.CustomerCart;
import com.nhat.ShoppingManagement.model.response.InvoiceResponse;
import com.nhat.ShoppingManagement.security.models.User;

import java.util.List;

public interface InvoiceService {
    boolean isEmpty();
    List<InvoiceResponse> findAll();
    InvoiceResponse findById(Long id);
    void createInvoice(User user, List<CustomerCart> customerCartList);
    void deleteInvoice(Long id);
}
