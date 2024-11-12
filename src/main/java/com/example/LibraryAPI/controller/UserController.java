package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.RegisterUserDto;
import com.example.LibraryAPI.Dto.UpdateUserDto;
import com.example.LibraryAPI.Dto.UserResponseDto;
import com.example.LibraryAPI.enums.RoleEnum;
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

import java.util.UUID;



@RequestMapping(UserController.baseControllerUri)
@RestController
public class UserController {

    public static final String baseControllerUri = "/user";
    @Autowired
    private  ModelMapper mapper;

    @Autowired
    private  UserService userService;

    @Autowired
    private  AuthenticationService authenticationService;




    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
            var user= mapper.map(currentUser, UserResponseDto.class);
        return new ResponseEntity<>( user, HttpStatus.OK);
    }



    @GetMapping("/users")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<Object> getAllUsers() {

        var users= userService.getAllUsers();

        return new ResponseEntity<>( users, HttpStatus.OK);


    }

    @GetMapping ("/id/{userId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable UUID userId){


        var user=userService.getUser(userId);

        return new ResponseEntity<>( user, HttpStatus.OK);

    }

    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserDto updateUserDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        var user =userService.updateUser(updateUserDto,currentUser.getId());

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        userService.deleteUser(currentUser.getId());


        return  ResponseEntity.noContent().build();

    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<UserResponseDto> registerAdmin(@Valid @RequestBody RegisterUserDto registerUserDto) {

        var registeredUser = authenticationService.signup(registerUserDto, RoleEnum.ROLE_ADMIN);

        return new ResponseEntity<>( registeredUser, HttpStatus.CREATED);


    }
    @GetMapping ("/{email}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String email){


        var user=userService.getUser(email);

        return new ResponseEntity<>( user, HttpStatus.OK);

    }


}
