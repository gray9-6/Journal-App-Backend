package com.ajay.journalApp.service;

import com.ajay.journalApp.entity.User;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    List<User> getAll();

    Optional<User> getUserById(ObjectId myId);

    boolean deleteUserById(ObjectId myId);


    User getUserByName(String username);
}
