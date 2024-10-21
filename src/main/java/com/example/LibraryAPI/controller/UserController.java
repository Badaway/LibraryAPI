package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.UpdateUserDto;
import com.example.LibraryAPI.Dto.UserResponseDto;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/user")
@RestController
public class UserController {
    private final ModelMapper mapper;

    private final UserService userService;

    public UserController(ModelMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
            var user= mapper.map(currentUser, UserResponseDto.class);
        return new ResponseEntity<>( user, HttpStatus.OK);
    }



    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        var users= userService.getAllUsers();
        var usersResponse=new ArrayList<UserResponseDto>();
        for (User i:users)
        {
            var user =mapper.map(i, UserResponseDto.class);
            usersResponse.add(user);
        }

        return new ResponseEntity<>( usersResponse, HttpStatus.OK);


    }

    @GetMapping ("/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> getUser(@PathVariable int userId){


        var user=userService.getUser(userId);
        if (user.isPresent()){
             var userResponse= mapper.map(user.get(),UserResponseDto.class);

            return new ResponseEntity<>( userResponse, HttpStatus.OK);
        }
        else
             return new ResponseEntity<>( "user does not exist", HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateUser(@RequestBody UpdateUserDto updateUserDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        if ( updateUserDto.getName()!=null )
            currentUser.setName(updateUserDto.getName());
        if ( updateUserDto.getPassword()!=null )
            currentUser.setPassword(updateUserDto.getPassword());
        var user =userService.updateUser(currentUser);
        var userResponse =mapper.map(user, UserResponseDto.class);
        return  new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();


        return userService.deleteUser(currentUser.getId());

    }





}
