package com.graphql.ShoppingCart.api;

import com.graphql.ShoppingCart.entity.CartInfo;
import com.graphql.ShoppingCart.entity.ProductInformation;
import com.graphql.ShoppingCart.entity.UserInformation;
import com.graphql.ShoppingCart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/carts")
public class CartResource {

    @Autowired
    private ICartService iCartService;

    @PostMapping("/")
    public ResponseEntity<CartInfo> createCart(@RequestBody CartInfo cartInfo){
        CartInfo cartCreated = iCartService.createCart(cartInfo);
       return new ResponseEntity<>(cartCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartInfo> getCartById(@PathVariable int cartId){
        Optional<CartInfo> cartById=iCartService.getCartById(cartId);
        return cartById.map(item -> new ResponseEntity<>(item,HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/all")
    public ResponseEntity<List<CartInfo>> getAllCart(){
        List<CartInfo> allCart = iCartService.getAllCart();
        return new ResponseEntity<>(allCart,HttpStatus.OK);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartInfo> updateCartValue(@PathVariable int cartId,@RequestBody CartInfo cartInfo){
        if(cartId!=cartInfo.getCartItemId()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            CartInfo updatedCart = iCartService.updateCart(cartInfo);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        }

        @DeleteMapping("/{cartItemId}")
        public ResponseEntity<Void> deleteCart(@PathVariable int cartItemId){
        try {
            iCartService.deleteCartItem(cartItemId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }

}
