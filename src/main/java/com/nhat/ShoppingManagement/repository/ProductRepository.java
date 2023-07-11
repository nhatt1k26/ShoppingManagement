package com.nhat.ShoppingManagement.repository;

import com.nhat.ShoppingManagement.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    boolean existsById(Long id);
}
