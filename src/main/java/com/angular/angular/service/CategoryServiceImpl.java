package com.angular.angular.service;

import com.angular.angular.entity.CategoryEntity;
import com.angular.angular.repository.CategoryRepository;
import com.angular.angular.request.CategoryRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        // search for category
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryRequest.getCategoryName());

        if (categoryEntity != null) throw new UsernameNotFoundException("Category is already exists");

        CategoryEntity categoryEntity1 = modelMapper.map(categoryRequest, CategoryEntity.class);

        categoryRepository.save(categoryEntity1);

    }

}
