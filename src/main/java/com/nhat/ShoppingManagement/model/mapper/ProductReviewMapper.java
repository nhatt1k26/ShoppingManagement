package com.nhat.ShoppingManagement.model.mapper;

import com.nhat.ShoppingManagement.model.dto.ProductReviewDTO;
import com.nhat.ShoppingManagement.model.entity.ProductReview;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductReviewMapper {
    ProductReviewMapper PRODUCT_REVIEW_MAPPER = Mappers.getMapper(ProductReviewMapper.class);
    void mapDtoToEntity(ProductReviewDTO source, @MappingTarget ProductReview target);
}
