package com.fishingstore.Product.Service;

import com.fishingstore.Category.Model.Category;
import com.fishingstore.Category.Repository.CategoryRepository;
import com.fishingstore.Exception.CategoryNotExist;
import com.fishingstore.Exception.ProductNotExist;
import com.fishingstore.Product.Model.Product;
import com.fishingstore.Product.Repository.ProductRepository;
import com.fishingstore.Web.Dto.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createNewProduct(ProductRequest productRequest) {

        Category category = categoryRepository.findById(UUID.fromString(productRequest.getCategoryId()))
                .orElseThrow(() -> new CategoryNotExist("Invalid category ID"));

      Product product = Product.builder()
              .name(productRequest.getName())
              .price(productRequest.getPrice())
              .description(productRequest.getDescription())
              .imageUrl(productRequest.getImageUrl())
              .price(productRequest.getPrice())
              .category(category)
              .build();

      productRepository.save(product);
      return product;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(UUID id) {

        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotExist("Invalid product ID"));
    }
}
