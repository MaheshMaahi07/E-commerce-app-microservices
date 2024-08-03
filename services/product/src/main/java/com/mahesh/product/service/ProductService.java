package com.mahesh.product.service;

import com.mahesh.product.dto.*;
import com.mahesh.product.entity.Category;
import com.mahesh.product.entity.Product;
import com.mahesh.product.exception.FourNotFour;
import com.mahesh.product.exception.ProductAlreadyExistException;
import com.mahesh.product.helper.ProductMapper;
import com.mahesh.product.repository.CategoryRepo;
import com.mahesh.product.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;



    private final  ProductMapper mapper;

    public Product addProduct(ProductRequest request) {
        Optional<Product> currentProduct= productRepo.findById(request.getId()==null?0: request.getId());
        if(currentProduct.isPresent()){
            throw new ProductAlreadyExistException("Product Already added");
        }
        Optional<Category> category= categoryRepo.findById(request.getCategoryId());
        if(category.isEmpty()){
            throw new FourNotFour("Category is not found");
        }
        Product product=mapper.toProduct(request);
        return productRepo.save(product);
    }


    public Category addCategory(CategoryRequest request) {
        Optional<Category> category= categoryRepo.findById(request.getId()==null?0:request.getId());
        if(category.isPresent()){
            throw new ProductAlreadyExistException("Category is already there");
        }
       Category category1= mapper.toCategory(request);
        return categoryRepo.save(category1);
}

    public List<Category> getCategory() {
        return categoryRepo.findAll();

    }

    public List<ProductResponse> findAllProducts() {
        return productRepo.findAll().stream()
                .map(mapper::toProductResponse)
                .sorted(Comparator.comparingInt(ProductResponse::getProductId))
                .collect(Collectors.toList());
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request)
     {
    //get all products id's entered/selected by user
            var productIds = request
                    .stream()
                    .map(ProductPurchaseRequest::getProductId)
                    .toList();
    //chec
            var storedProducts = productRepo.findAllByIdInOrderById(productIds);
            if (productIds.size() != storedProducts.size()) {
                throw new FourNotFour("One or more products does not exist");
            }
            var sortedRequest = request
                    .stream()
                    .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                    .toList();
            var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
            for (int i = 0; i < storedProducts.size(); i++) {
                var product = storedProducts.get(i);
                var productRequest = sortedRequest.get(i);
                if (product.getAvailableQuantity() < productRequest.getQuantity()) {
                    throw new FourNotFour("Insufficient stock quantity for product with ID:: " + productRequest.getProductId());
                }
                var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
                product.setAvailableQuantity(newAvailableQuantity);
                productRepo.save(product);
                purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.getQuantity()));
            }
            return purchasedProducts;
        }



    public ProductResponse findProductsById(Integer productId) {
        return productRepo.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(()->new EntityNotFoundException("Product Not found with ID::"+productId));


    }
}
