package com.ajay.journalApp.controller;

import com.ajay.journalApp.entity.User;
import com.ajay.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        try {
            return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @GetMapping("/id/{myId}")
//    public ResponseEntity<User> getUserById(@PathVariable("myId") ObjectId myId){
//        try {
//            Optional<User> optionalUser = userService.getUserById(myId);
//            if(optionalUser.isPresent()){
//                return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId myId){
//        try {
//            userService.deleteUserById(myId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            User foundUser = userService.getUserByName(userName);
            foundUser.setUserName(user.getUserName());
            foundUser.setPassword(user.getPassword());

            // save the user
            userService.saveUser(foundUser);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
