package com.graphql.ShoppingCart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@Table(name = "product_info")
public class ProductInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String productName;
    private String description;
    private Float price;
    private int quantityAvailable;
    private int categoryId;
    private String brand;
    private Float discount; // Use Float instead of Optional for simplicity
    // A product can belong to many carts, and a cart can have many products
    @ManyToMany(mappedBy = "productInfo")
    @JsonIgnore
    private List<CartInfo> carts;


}
