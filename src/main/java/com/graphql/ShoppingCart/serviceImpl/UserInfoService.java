package com.graphql.ShoppingCart.serviceImpl;

import com.graphql.ShoppingCart.repository.CartRepo;
import com.graphql.ShoppingCart.repository.ProductRepo;
import com.graphql.ShoppingCart.repository.UserRepo;
import com.graphql.ShoppingCart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserInfoService implements IUserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;
    @Override
    public com.graphql.ShoppingCart.entity.UserInformation createUser(com.graphql.ShoppingCart.entity.UserInformation user) {

        return userRepo.save(user);
    }

    @Override
    public Optional<com.graphql.ShoppingCart.entity.UserInformation> getUserById(Integer userId) {
        return userRepo.findById(userId);
    }

    @Override
    public List<com.graphql.ShoppingCart.entity.UserInformation> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public com.graphql.ShoppingCart.entity.UserInformation updateUser(com.graphql.ShoppingCart.entity.UserInformation user) {

        if(userRepo.existsById(user.getUserId())){
            return userRepo.save(user);
        }else {
            throw new RuntimeException("User not found with this ID :" + user.getUserId());
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        if (userRepo.existsById(userId)){
            userRepo.deleteById(userId);
        }else {

            throw new RuntimeException("User not found with this ID :" + userId);
        }

    }
}
