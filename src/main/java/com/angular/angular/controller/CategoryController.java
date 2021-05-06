package com.angular.angular.controller;

import com.angular.angular.request.CategoryRequest;
import com.angular.angular.request.ProductRequest;
import com.angular.angular.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CategoryRequest categoryRequest) {

        categoryService.createCategory(categoryRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
