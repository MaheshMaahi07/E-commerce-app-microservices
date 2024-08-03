package com.mahesh.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

   Integer id;
    @NotNull(message = "Category name is required")
    String name;
  String  description;
}
