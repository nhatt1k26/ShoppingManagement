package com.nhat.ShoppingManagement.service;

import com.nhat.ShoppingManagement.model.dto.ProductReviewDTO;
import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.model.entity.ProductReview;
import com.nhat.ShoppingManagement.model.mapper.ProductReviewMapper;
import com.nhat.ShoppingManagement.model.response.ProductReviewResponse;
import com.nhat.ShoppingManagement.repository.ProductRepository;
import com.nhat.ShoppingManagement.repository.ProductReviewRepository;
import com.nhat.ShoppingManagement.security.models.User;
import com.nhat.ShoppingManagement.security.repository.UserRepository;
import com.nhat.ShoppingManagement.service.interfaceService.ProductReviewService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductReviewServiceImpl implements ProductReviewService {
    ProductReviewRepository productReviewRepository;
    UserRepository userRepository;
    ProductRepository productRepository;
    ModelMapper modelMapper;

    @Override
    public boolean isEmpty() {
        return productReviewRepository.findAll().isEmpty();
    }

    @Override
    public List<ProductReviewResponse> findAll() {
        return productReviewRepository.findAll().stream().map(s->
                modelMapper.map(s, ProductReviewResponse.class)).collect(Collectors.toList());
    }

    @Override
    public ProductReviewResponse findById(Long id) {
        return null;
    }

    @Override
    public List<ProductReviewResponse> findByUserId(Long id){
        return productReviewRepository.findByUserId(id).stream().map(s->
                modelMapper.map(s, ProductReviewResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<ProductReviewResponse> findByProductId(Long id){
        return productReviewRepository.findByProductId(id).stream().map(s->
                modelMapper.map(s, ProductReviewResponse.class)).collect(Collectors.toList());
    };
    @Override
    public void addProductReview(Long userId,ProductReviewDTO productReviewDTO) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
        Product product = productRepository.findById(userId).orElseThrow(()->
                new EntityNotFoundException("User not found!"));
        ProductReview productReview = modelMapper.map(productReviewDTO,ProductReview.class);
        productReview.setUser(user);
        productReview.setProduct(product);
        productReviewRepository.save(productReview);
    }

    @Override
    public void updateProductReview(Long id, ProductReviewDTO productReviewDTO) {
        ProductReview productReview= productReviewRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("ProductReview not found!"));
        System.out.println(productReview);
        ProductReviewMapper.PRODUCT_REVIEW_MAPPER.mapDtoToEntity(productReviewDTO,productReview);
        System.out.println(productReview);
        productReviewRepository.save(productReview);
    }

    @Override
    public void deleteProductReview(Long id) {
        productReviewRepository.deleteById(id);
    }
}
