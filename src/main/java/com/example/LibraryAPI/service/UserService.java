package com.example.LibraryAPI.service;


import com.example.LibraryAPI.Dto.UpdateUserDto;
import com.example.LibraryAPI.Dto.UserResponseDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LibraryAPI.exceptions.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.example.LibraryAPI.exceptions.ExceptionMessage.userNotFoundByEmail;
import static com.example.LibraryAPI.exceptions.ExceptionMessage.userNotFoundById;

@Service
public class UserService {
    
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Mapper mapper;



    public User getUser(UUID id){


        return userRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException(userNotFoundById + id));


    }

    public List<User> getAllUsers(){

        return userRepository.findAll();


    }
    

    public void deleteUser(UUID id){

        if(userRepository.existsById(id)) {

            userRepository.deleteById(id);

        }

        else {
            throw new NoSuchElementException(userNotFoundById + id);
        }
    }
    
    
    public User updateUser(UpdateUserDto user, UUID id){

        var returnedUser= userRepository.findById(id)
                            .orElseThrow(() -> new NoSuchElementException(userNotFoundById + id));


        if(user.getName()!=null && !user.getName().isEmpty()){
            returnedUser.setName(user.getName());
        }

        if(user.getPassword()!=null && !user.getPassword().isEmpty()){
            returnedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }


        return userRepository.save(returnedUser);

       // return mapper.map(savedUser, UserResponseDto.class);
    }

    public User getUser(String email){


        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException(userNotFoundByEmail + email));

//        return  mapper.map(user,UserResponseDto.class);
    }
}