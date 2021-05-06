package com.angular.angular.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "CategoryEntity")
@Table(name = "categories")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 8088000463570195276L;

    @Id
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

}