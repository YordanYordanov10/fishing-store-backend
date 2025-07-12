package com.fishingstore.Web.Mapper;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Product.Model.Product;
import com.fishingstore.Web.Dto.CategoryResponse;
import com.fishingstore.Web.Dto.ProductResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static ProductResponse toProductResponse(Product product) {

        return ProductResponse.builder()
                .name(product.getName())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {

        return CategoryResponse.builder()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .categoryDescription(category.getDescription())
                .categoryImage(category.getImageUrl())
                .build();
    }
}
