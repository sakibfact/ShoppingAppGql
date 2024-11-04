package com.graphql.ShoppingCart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@Table(name = "user_info")
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String shippingAdd;
    private String billingAdd;
    private String phoneNum;

    // One user can have many carts
    @JsonIgnore
    @OneToMany(mappedBy = "userInformation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartInfo> carts;


}
