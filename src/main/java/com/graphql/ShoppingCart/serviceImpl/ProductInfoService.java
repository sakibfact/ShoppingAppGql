package com.graphql.ShoppingCart.serviceImpl;

import ch.qos.logback.classic.html.DefaultThrowableRenderer;
import com.graphql.ShoppingCart.entity.ProductInformation;
import com.graphql.ShoppingCart.repository.ProductRepo;
import com.graphql.ShoppingCart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductInfoService implements IProductService {
    @Autowired
    private ProductRepo productRepo;
    @Override
    public ProductInformation createProduct(ProductInformation product) {

        return productRepo.save(product);
    }

    @Override
    public Optional<ProductInformation> getProductById(Integer productId) {

        return productRepo.findById(productId);
    }

    @Override
    public List<ProductInformation> getAllProducts() {

        return productRepo.findAll();
    }

    @Override
    public ProductInformation updateProduct(ProductInformation product) {
        if(productRepo.existsById(product.getProductId())){
            return productRepo.save(product);
        }else {
            throw new RuntimeException("Product not found with ID :" +product.getProductId());
        }

    }
    @Override
    public void deleteProduct(Integer productId) {
        if(productRepo.existsById(productId)){
            productRepo.deleteById(productId);
        }else {
            throw new RuntimeException("Product not available for deletion with this ID :" + productId);
        }
    }
}
