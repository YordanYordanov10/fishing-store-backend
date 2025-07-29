package com.fishingstore.Web.Mapper;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Product.Model.Product;
import com.fishingstore.User.Model.User;
import com.fishingstore.Web.Dto.CategoryResponse;
import com.fishingstore.Web.Dto.ProductResponse;
import com.fishingstore.Web.Dto.UserResponse;
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
                .categoryId(product.getCategory().getId())
                .build();
    }

    public static List<ProductResponse> toProductResponseList(List<Product> products) {
        return products.stream().map(DtoMapper::toProductResponse).toList();
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

    public static UserResponse toUserResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public static List<UserResponse> toUserResponseList(List<User> users) {
        return users.stream().map(DtoMapper::toUserResponse).toList();
    }
}
