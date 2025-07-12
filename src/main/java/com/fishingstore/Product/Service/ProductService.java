package com.fishingstore.Product.Service;

import com.fishingstore.Product.Model.Product;
import com.fishingstore.Product.Repository.ProductRepository;
import com.fishingstore.Web.Dto.ProductRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public Product createNewProduct(ProductRequest productRequest) {

      Product product = Product.builder()
              .name(productRequest.getName())
              .price(productRequest.getPrice())
              .description(productRequest.getDescription())
              .imageUrl(productRequest.getImageUrl())
              .price(productRequest.getPrice())
              .category(productRequest.getCategory())
              .build();

      productRepository.save(product);
      return product;
    }
}
