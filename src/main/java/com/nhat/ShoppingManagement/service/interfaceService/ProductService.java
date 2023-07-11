package com.nhat.ShoppingManagement.service.interfaceService;

import com.nhat.ShoppingManagement.model.dto.ProductDTO;
import com.nhat.ShoppingManagement.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    boolean isEmpty();
    List<ProductResponse> findAll();
    ProductResponse findById(Long id);
    ProductResponse addProduct(ProductDTO newElement);
    ProductResponse updateProduct(Long id,ProductDTO updateElement);
//    ProductResponse patchProduct(Long id, ProductPatch bookingOfficePatch);
    void deleteProduct(Long id);
}
