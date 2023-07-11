package com.nhat.ShoppingManagement.repository;

import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.model.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDataRepository extends JpaRepository<ProductData, Long> {
    List<ProductData> findByProductId(Long productId);
}
