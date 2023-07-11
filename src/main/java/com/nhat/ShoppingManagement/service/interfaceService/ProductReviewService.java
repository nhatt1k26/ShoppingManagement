package com.nhat.ShoppingManagement.service.interfaceService;

import com.nhat.ShoppingManagement.model.dto.ProductReviewDTO;
import com.nhat.ShoppingManagement.model.response.ProductReviewResponse;

import java.util.List;

public interface ProductReviewService {
    boolean isEmpty();
    List<ProductReviewResponse> findAll();
    ProductReviewResponse findById(Long id);
    List<ProductReviewResponse> findByUserId(Long id);
    List<ProductReviewResponse> findByProductId(Long id);
    void addProductReview(Long userId, ProductReviewDTO newElement);
    void updateProductReview(Long id,ProductReviewDTO updateElement);
    //    ProductReviewResponse patchProductReview(Long id, ProductReviewPatch bookingOfficePatch);
    void deleteProductReview(Long id);
}
