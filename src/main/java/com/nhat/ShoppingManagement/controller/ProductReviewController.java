package com.nhat.ShoppingManagement.controller;


import com.nhat.ShoppingManagement.model.dto.ProductReviewDTO;
import com.nhat.ShoppingManagement.model.response.ProductReviewResponse;
import com.nhat.ShoppingManagement.service.interfaceService.ProductReviewService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productReview")
@AllArgsConstructor
public class ProductReviewController {
    private final Logger logger = LoggerFactory.getLogger(CustomerCartController.class);
    private final ProductReviewService productReviewService;
    private final ModelMapper modelMapper;


    @GetMapping("/all")
//    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAllProductReview(){
        logger.info("findAllProductReview");
        return ResponseEntity.ok(productReviewService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductReviewResponse> findProductReview(
            @PathVariable Long id
    ){
        logger.info("Find Product Review with id="+ id);
        return new ResponseEntity<>(productReviewService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductReviewResponse>> findProductReviewByUserId(
            @PathVariable Long userId
    ){
        return new ResponseEntity<>(productReviewService.findByUserId(userId), HttpStatus.OK);
    }



    @PostMapping("/user/{userId}/addProductReview")
//    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addProductReview(
            @PathVariable Long userId,
            @RequestBody ProductReviewDTO newProductReview
    )
    {
        productReviewService.addProductReview(userId,newProductReview);
        return new ResponseEntity<>("Update new productReview successful", HttpStatus.CREATED);
    }


    @PutMapping("/updateProductReview/{productId}")
//    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateProductReview(
            @PathVariable Long productId,
            @RequestBody ProductReviewDTO productReviewDTO
    )
    {
        productReviewService.updateProductReview(productId,productReviewDTO);
        return new ResponseEntity<>("Update new productReview successful", HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteById(
            @PathVariable Long id
    )
    {
        productReviewService.deleteProductReview(id);
        return new ResponseEntity<>("Deleted productReview",HttpStatus.OK);
    }
}
