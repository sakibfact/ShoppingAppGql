package com.graphql.ShoppingCart.repository;

import com.graphql.ShoppingCart.entity.CartInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<CartInfo,Integer> {
}
