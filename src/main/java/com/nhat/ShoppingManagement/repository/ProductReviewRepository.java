package com.nhat.ShoppingManagement.repository;

import com.nhat.ShoppingManagement.model.entity.CustomerCart;
import com.nhat.ShoppingManagement.model.entity.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview,Long> {
    List<ProductReview> findByUserId(Long userId);
    List<ProductReview> findByProductId(Long userId);
    Optional<ProductReview> findByUserIdAndProductId(Long userId, Long productId);
}
