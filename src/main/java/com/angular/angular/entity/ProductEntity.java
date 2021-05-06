package com.angular.angular.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "ProductEntity")
@Table(name = "products")
public class ProductEntity implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

    @Column(nullable = false, length = 25)
    private String productName;

    @Column(nullable = false)
    private String productImages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}