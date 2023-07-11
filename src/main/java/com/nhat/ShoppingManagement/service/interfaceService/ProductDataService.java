package com.nhat.ShoppingManagement.service.interfaceService;

import com.nhat.ShoppingManagement.model.response.ProductDataResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductDataService {
    List<ProductDataResponse> findAll();
    ProductDataResponse findById(Long productDataId);
    List<ProductDataResponse> findByProductId(Long productId);
    void uploadProductData(Long productId, MultipartFile multiPartFile);
//    void updateProductData(Long productDataId,MultipartFile updateElement);
    void deleteProductData(Long id);
}
