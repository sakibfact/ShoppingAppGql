package com.graphql.ShoppingCart.api;

import com.graphql.ShoppingCart.entity.ProductInformation;
import com.graphql.ShoppingCart.entity.UserInformation;
import com.graphql.ShoppingCart.service.IProductService;
import com.graphql.ShoppingCart.serviceImpl.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductResource {
    @Autowired
    private IProductService iProductService;

    @PostMapping("/")
    public ResponseEntity<ProductInformation> createProduct(@RequestBody ProductInformation product){
       ProductInformation productCreated= iProductService.createProduct(product);
       return new ResponseEntity<>(productCreated, HttpStatus.CREATED);


    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInformation> getProductById(@PathVariable int id){
        Optional<ProductInformation> productById = iProductService.getProductById(id);
        return new ResponseEntity<ProductInformation>(productById.get(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductInformation>> getAllProduct(){
        List<ProductInformation> allProducts = iProductService.getAllProducts();
        return new ResponseEntity<>(allProducts,HttpStatus.OK);

    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductInformation> updateProduct(@PathVariable int productId , @RequestBody ProductInformation productInfo){
        if (productId != productInfo.getProductId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {

            Optional<ProductInformation> existingProduct = iProductService.getProductById(productId);
            if (existingProduct.isPresent()) {
                ProductInformation updatedProducts = iProductService.updateProduct(productInfo);
                return new ResponseEntity<>(updatedProducts, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException re) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{pId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int pId){
       try{
           iProductService.deleteProduct(pId);
           ResponseEntity<Void> deleted = new ResponseEntity<>(HttpStatus.OK);
           return deleted;

       }catch (RuntimeException ex){

           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}
