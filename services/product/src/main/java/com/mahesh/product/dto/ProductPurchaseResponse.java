package com.mahesh.product.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductPurchaseResponse {
    public Integer productId;
    public String name;
    public String description;
    public BigDecimal price;
    public double quantity;

}
