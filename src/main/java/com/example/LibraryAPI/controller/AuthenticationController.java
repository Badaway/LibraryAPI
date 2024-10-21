package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.LoginUserDto;
import com.example.LibraryAPI.Dto.RegisterUserDto;
import com.example.LibraryAPI.Dto.UserResponseDto;
import com.example.LibraryAPI.Response.LoginResponse;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.service.AuthenticationService;
import com.example.LibraryAPI.service.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final ModelMapper mapper;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, ModelMapper mapper) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        var userResponse= mapper.map(registeredUser, UserResponseDto.class);
        return    new ResponseEntity<>( userResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse .setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

}
