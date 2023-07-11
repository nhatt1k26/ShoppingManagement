package com.nhat.ShoppingManagement.service;

import com.nhat.ShoppingManagement.model.entity.CustomerCart;
import com.nhat.ShoppingManagement.model.response.CustomerCartResponse;
import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.repository.CustomerCartRepository;
import com.nhat.ShoppingManagement.security.models.User;
import com.nhat.ShoppingManagement.service.interfaceService.CustomerCartService;
import com.nhat.ShoppingManagement.service.interfaceService.InvoiceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CustomerCartServiceImpl implements CustomerCartService {
    private final CustomerCartRepository customerCartRepository;
    private final InvoiceService invoiceService;
    private final ModelMapper modelMapper;

    @Override
    public boolean isEmpty(Long userId) {
        return customerCartRepository.findByUserId(userId).isEmpty();
    }

    @Override
    public List<CustomerCartResponse> findAll() {
        return customerCartRepository.findAll().stream().map(s -> modelMapper.map(s,CustomerCartResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerCartResponse findById(Long customerCartId) {
        return modelMapper.map(customerCartRepository
                .findById(customerCartId).orElseThrow(()-> new EntityNotFoundException("CustomerCart not found exception!")),
                CustomerCartResponse.class);
    }

    @Override
    public List<CustomerCartResponse> findByUser(User user) {
        return customerCartRepository
                .findByUserId(user.getId()).stream().map(s->modelMapper.map(s,CustomerCartResponse.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerCartResponse findByUserAndProduct(User user, Product product){
        return modelMapper.map(customerCartRepository
                .findByUserIdAndProductId(user.getId(),product.getId()).orElseThrow(
                        ()-> new EntityNotFoundException("CustomerCart not found exception!")
                ),
                CustomerCartResponse.class);
    }

    @Override
    public void addCustomerCart(User user, Product product, Integer quantity) {
        CustomerCart customerCartToAdd = new CustomerCart(user,product,quantity);
        customerCartRepository.save(customerCartToAdd);
    }


    @Override
    public void updateCustomerCart(Long customerCartId, Integer quantity){
        //check exists Cart
        CustomerCart customerCartToUpdate = customerCartRepository
                .findById(customerCartId).orElseThrow(()-> new EntityNotFoundException("CustomerCart not found exception!"));
        customerCartToUpdate.setItemQuantity(quantity);
        customerCartRepository.save(customerCartToUpdate);
    }

    @Override
    public void updateCustomerCart(User user, Product product, Integer quantity){
        //check exists Cart
        CustomerCart customerCartToUpdate = customerCartRepository
                .findByUserIdAndProductId(user.getId(),product.getId()).orElseThrow(()-> new EntityNotFoundException("CustomerCart not found exception!"));

        System.out.println(customerCartToUpdate.getId());
        customerCartToUpdate.setItemQuantity(quantity);
        System.out.println(customerCartToUpdate);
        customerCartRepository.save(customerCartToUpdate);
    }

    @Override
    public void purchaseCustomerCart(User user){
        if (findByUser(user).isEmpty())
            throw new EntityNotFoundException("Not exist product in cart");
        List<CustomerCart> customerCartList = customerCartRepository.findByUserId(user.getId());
        invoiceService.createInvoice(user,customerCartList);
        deleteCustomerCartByUserId(user.getId());
    }


    @Override
    public void deleteCustomerCart(User user, Product product) {
        CustomerCart customerCartToDelete = customerCartRepository
                .findByUserIdAndProductId(user.getId(),product.getId()).orElseThrow(()-> new EntityNotFoundException("CustomerCart not found exception!"));
        customerCartRepository.deleteById(customerCartToDelete.getId());
    }

    @Override
    public void deleteCustomerCart(Long customerCartId) {
        customerCartRepository.findById(customerCartId).orElseThrow(()-> new EntityNotFoundException("CustomerCart not found exception!"));
        customerCartRepository.deleteById(customerCartId);
    }

    @Override
    public void deleteCustomerCartByUserId(Long userId){
        List<CustomerCart> customerCartList = customerCartRepository.findByUserId(userId);
        customerCartList.stream().forEach(s -> customerCartRepository.deleteById(s.getId()));
    }
}
