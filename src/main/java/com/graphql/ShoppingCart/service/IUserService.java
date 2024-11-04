package com.graphql.ShoppingCart.service;

import com.graphql.ShoppingCart.entity.UserInformation;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserInformation createUser(UserInformation user);

    Optional<UserInformation> getUserById(Integer userId);

    List<UserInformation> getAllUsers();

    UserInformation updateUser(UserInformation user);

    void deleteUser(Integer userId);

}
