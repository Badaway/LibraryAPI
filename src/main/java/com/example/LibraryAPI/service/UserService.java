package com.example.LibraryAPI.service;

import com.example.LibraryAPI.model.Role;
import com.example.LibraryAPI.model.RoleEnum;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository ,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder= passwordEncoder;
    }

    public Optional<User> getUser(int id){


        return userRepository.findById(id);

        /*

       */
    }

    public List<User> getAllUsers(){

        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);


        return  users;
        /*if (!users.isEmpty())
            return new ResponseEntity<>( users, HttpStatus.OK);
        return new ResponseEntity<>( "NA ", HttpStatus.BAD_REQUEST);*/
    }


    public User newUser(User user){
        userRepository.save(user);
        return  user;
    }

    public ResponseEntity<Object> deleteUser(int id){

        userRepository.deleteById(id);

        return  new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    public User updateUser(User user){


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
}