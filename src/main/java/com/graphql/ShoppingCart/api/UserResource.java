package com.graphql.ShoppingCart.api;

import com.graphql.ShoppingCart.entity.UserInformation;
import com.graphql.ShoppingCart.service.IUserService;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private IUserService iUserService;
    @PostMapping("/")
    public ResponseEntity<UserInformation> createUser(@RequestBody UserInformation user) {

       UserInformation created= iUserService.createUser(user);

       return new ResponseEntity<>(created, HttpStatus.CREATED);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInformation> getUserById(@PathVariable Integer userId){

        Optional<UserInformation> user=iUserService.getUserById(userId);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.FOUND);

        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

 }

 @GetMapping("/allUsers")
 public ResponseEntity <List<UserInformation>> getAllUser(){
 List<UserInformation> allUsers= iUserService.getAllUsers();
 return new ResponseEntity<>(allUsers, HttpStatus.OK);

   }

   @PutMapping("/{userId}")
   public ResponseEntity <UserInformation> updateUser(@PathVariable int userId,@RequestBody UserInformation userInformation){

        if(userId!=(userInformation.getUserId())){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            UserInformation updatedUser=iUserService.updateUser(userInformation);

            return new ResponseEntity<>(updatedUser, HttpStatus.OK);

        }catch (RuntimeException re){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);


        }

   }

   @DeleteMapping("/{userId}")
   public ResponseEntity<Void> deleteUser(@PathVariable int userId){

        try{
            iUserService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }catch (RuntimeException e){

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
   }

}
