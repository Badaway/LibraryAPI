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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService {
    
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Mapper mapper;



    public UserResponseDto getUser(UUID id){


        var user= userRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));

        return  mapper.map(user,UserResponseDto.class);
    }

    public List<UserResponseDto> getAllUsers(){

        var users=userRepository.findAll();

        var usersResponse =new ArrayList<UserResponseDto>();

        for (User user:users)
        {
            var userResponseDto =mapper.map(user, UserResponseDto.class);
            usersResponse.add(userResponseDto);
        }

        return  usersResponse;
    }
    

    public void deleteUser(UUID id){

        if(userRepository.existsById(id)) {

            userRepository.deleteById(id);

        }

        throw  new NoSuchElementException("User with id: "+id+" does not exist");
    }
    
    
    public UserResponseDto updateUser(UpdateUserDto user, UUID id){

        var returnedUser= userRepository.findById(id)
                            .orElseThrow(() -> new NoSuchElementException("User not found with id " + id));


        if(user.getName()!=null && !user.getName().isEmpty()){
            returnedUser.setName(user.getName());
        }

        if(user.getPassword()!=null && !user.getPassword().isEmpty()){
            returnedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }


        var savedUser =userRepository.save(returnedUser);

        return mapper.map(savedUser, UserResponseDto.class);
    }

    public UserResponseDto getUser(String email){


        var user= userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("User not found with email " + email));

        return  mapper.map(user,UserResponseDto.class);
    }
}