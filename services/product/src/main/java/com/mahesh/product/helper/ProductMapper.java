package com.mahesh.product.helper;

import com.mahesh.product.dto.CategoryRequest;
import com.mahesh.product.dto.ProductPurchaseResponse;
import com.mahesh.product.dto.ProductRequest;
import com.mahesh.product.dto.ProductResponse;
import com.mahesh.product.entity.Category;
import com.mahesh.product.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .availableQuantity(request.getAvailableQuantity())
                .price(request.getPrice())
                .category(Category.builder()
                        .id(request.getCategoryId()).build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return  ProductResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getAvailableQuantity())
                .price(product.getPrice())
                .build();

    }

    public Category toCategory(CategoryRequest request) {
        return Category.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }


    public ProductPurchaseResponse toproductPurchaseResponse(Product product, double quantity) {
        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(quantity)
                .build();

    }
}
