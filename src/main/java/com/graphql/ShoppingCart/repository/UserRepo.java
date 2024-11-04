package com.graphql.ShoppingCart.repository;

import com.graphql.ShoppingCart.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserInformation,Integer> {
}
