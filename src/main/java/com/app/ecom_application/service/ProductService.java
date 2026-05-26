package com.app.ecom_application.service;

import com.app.ecom_application.dto.ProductRequest;
import com.app.ecom_application.dto.ProductResponse;
import com.app.ecom_application.model.Product;
import com.app.ecom_application.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public ProductResponse createProduct(ProductRequest productRequest) {
       Product product = new Product();
       updateProductFromRequest(product , productRequest);
       Product savedProduct = productRepo.save(product);
       return mapToProductResponse(savedProduct);
    }
    public Optional<ProductResponse> updateProduct(Long id , ProductRequest productRequest) {
        return productRepo.findById(id)
                .map(existedProduct -> {
                    updateProductFromRequest(existedProduct , productRequest);
                    Product savedProduct = productRepo.save(existedProduct);
                    return mapToProductResponse(savedProduct);
                });
    }
    public List<ProductResponse> getAllProducts() {
        return productRepo.findByActiveTrue()
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }
    public boolean deleteProduct(Long id) {
        return productRepo.findById(id)
                .map(existedProduct -> {
                    existedProduct.setActive(false);
                    productRepo.save(existedProduct);
                    return true;
                }).orElse(false);
    }
    public List<ProductResponse> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword)
                .stream()
                .map(this:: mapToProductResponse)
                .collect(Collectors.toList());
    }
    private ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(savedProduct.getId());
        productResponse.setName(savedProduct.getName());
        productResponse.setDescription(savedProduct.getDescription());
        productResponse.setCategory(savedProduct.getCategory());
        productResponse.setPrice(savedProduct.getPrice());
        productResponse.setImageUrl(savedProduct.getImageUrl());
        productResponse.setStockQuantity(savedProduct.getStockQuantity());
        return productResponse;
    }

    private void updateProductFromRequest(Product product , ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());
    }



}
