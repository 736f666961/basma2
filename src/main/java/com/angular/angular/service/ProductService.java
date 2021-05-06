package com.angular.angular.service;

import com.angular.angular.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ProductService {

    void createProduct(ProductRequest productRequest);

    void uploadProductImage(MultipartFile[] images, ArrayList<String> imgNames);
}
