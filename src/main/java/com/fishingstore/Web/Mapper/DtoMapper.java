package com.fishingstore.Web.Mapper;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Product.Model.Product;
import com.fishingstore.Web.Dto.CategoryResponse;
import com.fishingstore.Web.Dto.ProductResponse;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DtoMapper {

    public static ProductResponse toProductResponse(Product product) {

        return ProductResponse.builder()
                .name(product.getName())
                .id(product.getId())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .imageUrl(product.getImageUrl())
                .category(product.getCategory())
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

    public static List<CategoryResponse> toCategoryResponseList(List<Category> categories) {

        return categories.stream().map(DtoMapper::toCategoryResponse).toList();
    }
}
