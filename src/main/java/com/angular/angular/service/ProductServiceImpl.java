package com.angular.angular.service;

import com.angular.angular.entity.CategoryEntity;
import com.angular.angular.entity.ProductEntity;
import com.angular.angular.repository.CategoryRepository;
import com.angular.angular.repository.ProductRepository;
import com.angular.angular.request.ProductRequest;
import com.angular.angular.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Utils utils;

    @Value("${uploaded_images_config}")
    private String UPLOADS_DIRECTORY;

    @Override
    public void createProduct(ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();

        // Store image names
        String imgName = "";
        ArrayList<String> imageNames = new ArrayList<>();
        String tempImg = "";

        for (int i = 0; i < productRequest.getProductImages().length; i++) {
            tempImg = utils.generatePublicUserId(25);
            imgName += tempImg;

            if (!(i == productRequest.getProductImages().length - 1)) imgName += ",";

            imageNames.add(tempImg);
        }

        // Set product name and product image
        productEntity.setProductName(productRequest.getProductName());
        productEntity.setProductImages(imgName);

        // search for category
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(productRequest.getProductCategory());

        // if category does exist
        if (categoryEntity == null) throw new UsernameNotFoundException("Category does not exists");

        // check size of product images
        if (!(productRequest.getProductImages().length >= 4 && productRequest.getProductImages().length <= 8))
            throw new UsernameNotFoundException("Product images must be between 4 and 8 images ");

        // set category for product
        productEntity.setCategory(categoryEntity);

        // save data to database
        productRepository.save(productEntity);

        // upload image to server
        uploadProductImage(productRequest.getProductImages(), imageNames);
    }

    @Override
    public void uploadProductImage(MultipartFile[] images, ArrayList<String> imageNames) {
        // File Output Stream which writes bytes to the server
        FileOutputStream fileOutputStream = null;

        try {

            int counter = 0;

            for (MultipartFile image : images) {
                // Upload image to the server
                fileOutputStream = new FileOutputStream(UPLOADS_DIRECTORY + imageNames.get(counter));
                fileOutputStream.write(image.getBytes());

                counter++;
            }

            fileOutputStream.close();

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
