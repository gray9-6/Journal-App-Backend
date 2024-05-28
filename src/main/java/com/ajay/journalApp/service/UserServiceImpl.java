package com.ajay.journalApp.service;

import com.ajay.journalApp.entity.JournalEntry;
import com.ajay.journalApp.entity.User;
import com.ajay.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



//    public void saveUser(User user){
//        try {
//            userRepository.save(user);
//        }catch (Exception e){
//            log.error(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    public void saveUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(ObjectId myId) {
        return userRepository.findById(myId);
    }

    @Override
    public boolean deleteUserById(ObjectId myId) {
        userRepository.deleteById(myId);
        return true;
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUserName(username).orElseThrow(()-> new RuntimeException("user Not Found"));
    }
}
