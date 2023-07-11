package com.nhat.ShoppingManagement.service;

import com.nhat.ShoppingManagement.model.entity.Product;
import com.nhat.ShoppingManagement.model.entity.ProductData;
import com.nhat.ShoppingManagement.model.response.ProductDataResponse;
import com.nhat.ShoppingManagement.repository.ProductDataRepository;
import com.nhat.ShoppingManagement.repository.ProductRepository;
import com.nhat.ShoppingManagement.service.interfaceService.ProductDataService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Data
public class ProductDataServiceImpl implements ProductDataService {
    private final ModelMapper modelMapper;
    private final ProductDataRepository productDataRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDataResponse> findAll() {
        return productDataRepository.findAll().stream().map(
                s-> modelMapper.map(s,ProductDataResponse.class)
        ).collect(Collectors.toList());
    }

    @Override
    public ProductDataResponse findById(Long productDataId) {
        return modelMapper.map(productDataRepository.findById(productDataId),ProductDataResponse.class);
    }

    @Override
    public List<ProductDataResponse> findByProductId(Long productId) {
        return productDataRepository.findByProductId(productId).stream().map(
                s-> modelMapper.map(s,ProductDataResponse.class)
        ).collect(Collectors.toList());
    }


    @Override
    public void uploadProductData(Long productId, MultipartFile multiPartFile) {
        Product product = productRepository.findById(productId).orElseThrow(()->new EntityNotFoundException("Product with id=" + productId+"not found"));
        try {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multiPartFile.getOriginalFilename()));
            ProductData productData = new ProductData();
            productData.setName(fileName);
            productData.setType(multiPartFile.getContentType());
            productData.setData(multiPartFile.getBytes());
            productData.setProduct(product);
            productDataRepository.save(productData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProductData(Long productDataId) {
        productDataRepository.findById(productDataId).orElseThrow(()->
                new EntityNotFoundException("Product data with id = "+ productDataId + " not found"));
    }
}
