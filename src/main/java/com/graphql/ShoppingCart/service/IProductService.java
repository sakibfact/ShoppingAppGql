package com.graphql.ShoppingCart.service;

import com.graphql.ShoppingCart.entity.ProductInformation;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    ProductInformation createProduct(ProductInformation product);
    Optional<ProductInformation> getProductById(Integer productId);
    List<ProductInformation> getAllProducts();
    ProductInformation updateProduct(ProductInformation product);
    void deleteProduct(Integer productId);
}
