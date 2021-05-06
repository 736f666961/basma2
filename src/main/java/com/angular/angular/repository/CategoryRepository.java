package com.angular.angular.repository;

import com.angular.angular.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, String> {
    CategoryEntity findByCategoryName(String categoryName);
}
