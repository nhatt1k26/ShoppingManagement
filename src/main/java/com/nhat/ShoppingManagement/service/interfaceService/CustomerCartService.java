package com.nhat.ShoppingManagement.service.interfaceService;

import com.nhat.ShoppingManagement.model.response.CustomerCartResponse;
import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.security.models.User;

import java.util.List;

public interface CustomerCartService {
    boolean isEmpty(Long id);
    List<CustomerCartResponse> findAll();
    CustomerCartResponse findById(Long cartId);
    List<CustomerCartResponse> findByUser(User user);
    CustomerCartResponse findByUserAndProduct(User user, Product product);
    void addCustomerCart(User user, Product product, Integer quantity);
    void updateCustomerCart(Long customerCartId,Integer quantity);
    void updateCustomerCart(User user, Product product,Integer quantity);
    void purchaseCustomerCart(User user);
    //    ProductResponse patchProduct(Long id, ProductPatch bookingOfficePatch);
    void deleteCustomerCart(User user, Product product);
    void deleteCustomerCart(Long customerCartId);
    void deleteCustomerCartByUserId(Long userId);
}
