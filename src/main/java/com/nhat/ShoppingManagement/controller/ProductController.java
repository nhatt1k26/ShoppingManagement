package com.nhat.ShoppingManagement.controller;

import com.nhat.ShoppingManagement.model.dto.ProductDTO;
import com.nhat.ShoppingManagement.model.response.ProductResponse;
import com.nhat.ShoppingManagement.service.interfaceService.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(CustomerCartController.class);
    private final ProductService productService;
    private final ModelMapper modelMapper;


    @GetMapping("/all")
//    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAllProduct(){
        logger.info("Find all product is call!");
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProduct(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addProduct(
            @RequestBody ProductDTO newProduct
    )
    {
        productService.addProduct(newProduct);
        return new ResponseEntity<>("Add new product successful", HttpStatus.CREATED);
    }




    @PutMapping("/updateProduct/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO newProduct
    )
    {
        productService.updateProduct(id,newProduct);
        return new ResponseEntity<>("Update new product successful", HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(
            @PathVariable Long id
    )
    {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Deleted product",HttpStatus.OK);
    }
}
