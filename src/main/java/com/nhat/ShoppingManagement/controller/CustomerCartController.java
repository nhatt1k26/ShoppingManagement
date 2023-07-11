package com.nhat.ShoppingManagement.controller;

import com.nhat.ShoppingManagement.model.dto.CustomerCartDTO;
import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.repository.CustomerCartRepository;
import com.nhat.ShoppingManagement.repository.ProductRepository;
import com.nhat.ShoppingManagement.security.models.User;
import com.nhat.ShoppingManagement.security.repository.UserRepository;
import com.nhat.ShoppingManagement.service.interfaceService.CustomerCartService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customerCart")
public class CustomerCartController {
    private final Logger logger = LoggerFactory.getLogger(CustomerCartController.class);
    private final CustomerCartRepository customerCartRepository;
    private final CustomerCartService customerCartService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @GetMapping("/all")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?> findAll(){

        return ResponseEntity.ok(customerCartService.findAll());
    }

    @GetMapping("/cart/{customerCartId}")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?> findCustomerCartById(@PathVariable Long customerCartId){
        return ResponseEntity.ok(customerCartService.findById(customerCartId));
    }

    @GetMapping("/user/{userId}")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?> findCustomerCartByUserId(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
        logger.info("user= {}",userId);

        return ResponseEntity.ok(customerCartService.findByUser(user));
    }

    @PostMapping("/user/{userId}/addCustomerCart")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?> addCustomerCart(@PathVariable Long userId,@RequestBody CustomerCartDTO customerCartDTO){
        System.out.println("den day roi");
        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
        Product product = productRepository.findById(customerCartDTO.getProductId()).orElseThrow(()->
                new EntityNotFoundException("Product not found!"));
        customerCartService.addCustomerCart(user,product,customerCartDTO.getQuantity());
        return ResponseEntity.ok("Add CustomerCart succesfull!");
    }
    @PutMapping("/update/{userId}")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?>
    updateCustomerCart
             (@PathVariable Long userId, @RequestBody CustomerCartDTO customerCartDTO)
    {
        Long productId = customerCartDTO.getProductId();
        Integer quantity = customerCartDTO.getQuantity();
        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
        Product product = productRepository.findById(productId).orElseThrow(()->
                new EntityNotFoundException("Product not found!"));
        customerCartService.updateCustomerCart(user,product,quantity);
        return ResponseEntity.ok("Update succesful");
    }
    @PutMapping("/user/{id}/purchase")
    public ResponseEntity<?> purchaseCustomerCart(
            @PathVariable Long id
    ){
        User user = userRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
        customerCartService.purchaseCustomerCart(user);
        return ResponseEntity.ok("Purchase successful");
    }

    @PutMapping("/updateById")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?>
    addCustomerCart
            (@RequestParam Long customerCartId, @RequestBody Integer quantity)
    {
        customerCartService.updateCustomerCart(customerCartId,quantity);
        return ResponseEntity.ok("Update succesful");
    }

    @DeleteMapping("/user/{userId}/deleteAll")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?>
    deleteAllCustomerCartById
            (@PathVariable Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found!"));

        customerCartService.deleteCustomerCartByUserId(userId);
        return ResponseEntity.ok("Delete all by userId succesful");
    }

    @DeleteMapping("/deleteAll")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?>
    deleteCustomerCart
            (@RequestParam Long userId,@RequestParam Long productId)
    {
        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
        Product product = productRepository.findById(productId).orElseThrow(()->
                new EntityNotFoundException("Product not found!"));
        customerCartService.deleteCustomerCart(user,product);
        return ResponseEntity.ok("Update succesful");
    }

    @DeleteMapping("/deleteById")
    // Middleware: check exists id and verify email, check privilege, check userId=token?
    public ResponseEntity<?>
    deleteCustomerCart
            (@RequestParam Long customerCartId, @RequestBody Integer quantity)
    {
        customerCartService.deleteCustomerCart(customerCartId);
        return ResponseEntity.ok("Update succesful");
    }
}
