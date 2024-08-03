package com.mahesh.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
//    @OneToMany: This annotation indicates that the relationship between the entities is one-to-many,
//    meaning that one instance of the entity that owns this field can be associated with multiple instances
//    of the Product entity.
//    mappedBy = "category": This attribute specifies the field in the Product entity that owns
//    the relationship. In this case, it's the category field in the Product entity.
//    This is the inverse side of the relationship.
//    cascade = CascadeType.REMOVE: This attribute specifies the cascade type for the relationship.
//    In this case, it's set to REMOVE, which means that when the owning entity (the Category instance) is
//    removed, all associated Product instances will also be removed.
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Product> products;
}
