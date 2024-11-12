package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.LoginUserDto;
import com.example.LibraryAPI.Dto.RegisterUserDto;
import com.example.LibraryAPI.Response.LoginResponse;
import com.example.LibraryAPI.enums.RoleEnum;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.service.AuthenticationService;
import com.example.LibraryAPI.service.JwtService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(AuthenticationController.baseControllerUri)
@RestController
public class AuthenticationController {

    public static final String baseControllerUri = "/auth";


    @Autowired
    private  JwtService jwtService;

    @Autowired
    private  AuthenticationService authenticationService;
    @Autowired
    private  ModelMapper mapper;



    @PostMapping("/signup")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterUserDto registerUserDto) {

        var registeredUser = authenticationService.signup(registerUserDto, RoleEnum.ROLE_USER);



        return  new ResponseEntity<>( registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

}