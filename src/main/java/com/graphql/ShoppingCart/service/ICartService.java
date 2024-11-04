package com.graphql.ShoppingCart.service;

import com.graphql.ShoppingCart.entity.CartInfo;
import com.graphql.ShoppingCart.entity.ProductInformation;
import com.graphql.ShoppingCart.entity.UserInformation;

import java.util.List;
import java.util.Optional;

public interface ICartService {

    CartInfo createCart(CartInfo cart);
    Optional<CartInfo> getCartById(int cartItemId);
    List<CartInfo> getAllCart();
    CartInfo updateCart(CartInfo cart);
     void deleteCartItem(int cartItemId);

}
