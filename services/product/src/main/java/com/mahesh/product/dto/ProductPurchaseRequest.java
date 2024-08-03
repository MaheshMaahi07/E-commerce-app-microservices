package com.mahesh.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductPurchaseRequest {
    @NotNull(message = "Product is mandatory")
    Integer productId;
    @Positive(message = "Quantity is mandatory")
    double quantity;
}
