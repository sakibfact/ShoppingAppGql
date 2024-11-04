package com.graphql.ShoppingCart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "cart_info")
public class CartInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartItemId;

    private int sessionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedOn;

    // Many carts can belong to one user
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserInformation userInformation;


    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "cart_product", // Create a join table named 'cart_product'
            joinColumns = @JoinColumn(name = "cart_item_id"), // Join with CartInfo
            inverseJoinColumns = @JoinColumn(name = "product_id") // Join with ProductInformation
    )
    private List<ProductInformation> productInfo;
}