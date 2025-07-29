package com.fishingstore.Web;

import com.fishingstore.Product.Model.Product;
import com.fishingstore.Product.Service.ProductService;
import com.fishingstore.Web.Dto.ProductRequest;
import com.fishingstore.Web.Dto.ProductResponse;
import com.fishingstore.Web.Mapper.DtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/products")
    ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest){

        Product product = productService.createNewProduct(productRequest);
        ProductResponse productResponse = DtoMapper.toProductResponse(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);

    }

    @GetMapping("/products")
    ResponseEntity<List<ProductResponse>> getAllProducts(){

       List<Product> productList = productService.findAllProducts();
       List<ProductResponse> productResponseList = DtoMapper.toProductResponseList(productList);
       return ResponseEntity.status(HttpStatus.OK).body(productResponseList);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id){

        Product product = productService.findProductById(id);
        ProductResponse productResponse = DtoMapper.toProductResponse(product);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

}
