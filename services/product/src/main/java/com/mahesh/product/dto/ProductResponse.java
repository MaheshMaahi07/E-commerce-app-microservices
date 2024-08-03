package com.mahesh.product.dto;

import com.mahesh.product.entity.Category;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {
    public Integer productId;
    public String name;
    public String description;
    public BigDecimal price;
    public double quantity;

}
