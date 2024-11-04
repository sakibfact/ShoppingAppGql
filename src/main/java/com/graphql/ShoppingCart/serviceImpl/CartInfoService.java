package com.graphql.ShoppingCart.serviceImpl;

import com.graphql.ShoppingCart.entity.CartInfo;
import com.graphql.ShoppingCart.entity.ProductInformation;
import com.graphql.ShoppingCart.entity.UserInformation;
import com.graphql.ShoppingCart.repository.CartRepo;
import com.graphql.ShoppingCart.repository.ProductRepo;
import com.graphql.ShoppingCart.repository.UserRepo;
import com.graphql.ShoppingCart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartInfoService implements ICartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Override
    public CartInfo createCart(CartInfo cart ) {
        cart.setCreatedAt(LocalDateTime.now());
        UserInformation user = userRepo.findById(cart.getUserInformation().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<ProductInformation> products=new ArrayList<>();
        for(ProductInformation product : cart.getProductInfo()){

            ProductInformation fetchedProduct =productRepo.findById(product.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID :" +product
                            .getProductId()));
                    products.add(fetchedProduct);

        }
        cart.setUserInformation(user);
        cart.setProductInfo(products);
        return cartRepo.save(cart);
    }

    @Override
    public Optional<CartInfo> getCartById(int cartItemId) {
        Optional<CartInfo> cartItemById = cartRepo.findById(cartItemId);
        return cartItemById;
    }

    @Override
    public List<CartInfo> getAllCart() {
      return cartRepo.findAll();
    }

    @Override
    public CartInfo updateCart(CartInfo cart) {
        if(cartRepo.existsById(cart.getCartItemId())){
            cart.setUpdatedOn(LocalDateTime.now());
            return cartRepo.save(cart);
        }else {
            throw new RuntimeException("Cart item not found with this ID :" +cart.getCartItemId());

        }
    }

    @Override
    public void deleteCartItem(int cartItemId) {
        if(cartRepo.existsById(cartItemId)){
            cartRepo.deleteById(cartItemId);

        }else {
            throw new RuntimeException("Cart not found with ID : " +cartItemId);
        }
    }
}
