package com.mahesh.product.controller;

import com.mahesh.product.dto.*;
import com.mahesh.product.entity.Category;
import com.mahesh.product.entity.Product;
import com.mahesh.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService service;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody  ProductRequest request){
        return ResponseEntity.ok(service.addProduct(request));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(service.findAllProducts());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findProductById(@Valid @PathVariable("product-id") Integer productId){
        return ResponseEntity.ok(service.findProductsById(productId));
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryRequest request){
        return ResponseEntity.ok(service.addCategory(request));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategory(){
        return ResponseEntity.ok(service.getCategory());
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody @Valid List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(service.purchaseProducts(request));
    }

}
