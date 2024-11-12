package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.RegisterUserDto;
import com.example.LibraryAPI.Dto.UpdateUserDto;
import com.example.LibraryAPI.Dto.UserResponseDto;
import com.example.LibraryAPI.enums.RoleEnum;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.service.AuthenticationService;
import com.example.LibraryAPI.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@RequestMapping(UserController.baseControllerUri)
@RestController
public class UserController {

    public static final String baseControllerUri = "/user";

    public static final String currentUserUri = "/me";
    public static final String getAllUsersUri = "/users";
    public static final String getUserByIdUri = "/id/{userId}";
    public static final String updateUserUri = "/me";
    public static final String deleteUserUri = "/me";
    public static final String registerAdminUri="/admin";
    public static final String getUserByEmailUri ="/{email}";


    @Autowired
    private Mapper mapper;

    @Autowired
    private  UserService userService;

    @Autowired
    private  AuthenticationService authenticationService;




    @GetMapping(currentUserUri)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
            var user= mapper.map(currentUser, UserResponseDto.class);
        return new ResponseEntity<>( user, HttpStatus.OK);
    }



    @GetMapping(getAllUsersUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        var users= userService.getAllUsers();

        var usersResponse =new ArrayList<UserResponseDto>();

        for (User user:users)
        {
            var userResponseDto =mapper.map(user, UserResponseDto.class);
            usersResponse.add(userResponseDto);
        }



        return new ResponseEntity<>( usersResponse, HttpStatus.OK);


    }

    @GetMapping (getUserByIdUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable UUID userId){


        var user=userService.getUser(userId);

        var userResponse=mapper.map(user, UserResponseDto.class);

        return new ResponseEntity<>( userResponse, HttpStatus.OK);

    }

    @PutMapping(updateUserUri)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserDto updateUserDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        var user =userService.updateUser(updateUserDto,currentUser.getId());

        var userResponse=mapper.map(user, UserResponseDto.class);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @DeleteMapping(deleteUserUri)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        userService.deleteUser(currentUser.getId());


        return  ResponseEntity.noContent().build();

    }

    @PostMapping(registerAdminUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<UserResponseDto> registerAdmin(@Valid @RequestBody RegisterUserDto registerUserDto) {

        var registeredUser = authenticationService.signup(registerUserDto, RoleEnum.ROLE_ADMIN);

        var userResponse=mapper.map(registeredUser, UserResponseDto.class);

        return new ResponseEntity<>( userResponse, HttpStatus.CREATED);


    }
    @GetMapping (getUserByEmailUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String email){


        var user=userService.getUser(email);

        var userResponse=mapper.map(user, UserResponseDto.class);

        return new ResponseEntity<>( userResponse, HttpStatus.OK);

    }


}
