package com.angular.angular.controller;

import com.angular.angular.request.ProductRequest;
import com.angular.angular.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Void> createProduct(ProductRequest productRequest) {

        productService.createProduct(productRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
