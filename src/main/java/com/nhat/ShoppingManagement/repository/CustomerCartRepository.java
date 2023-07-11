package com.nhat.ShoppingManagement.repository;

import com.nhat.ShoppingManagement.model.entity.CustomerCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerCartRepository extends JpaRepository<CustomerCart,Long> {
    List<CustomerCart> findByUserId(Long userId);
//    Optional<CustomerCart> findByCustomerCartId(Long id);
    Optional<CustomerCart> findByUserIdAndProductId(Long userId, Long productId);
    void deleteByUserId(Long userId);
}
