package com.graphql.ShoppingCart.repository;

import com.graphql.ShoppingCart.entity.ProductInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductInformation,Integer> {
}
