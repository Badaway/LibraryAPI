package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.LoginUserDto;
import com.example.LibraryAPI.Dto.RegisterUserDto;
import com.example.LibraryAPI.Dto.UserResponseDto;
import com.example.LibraryAPI.enums.RoleEnum;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Role;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.repository.RoleRepository;
import com.example.LibraryAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;


@Service
public class AuthenticationService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private Mapper mapper;



    public UserResponseDto signup(RegisterUserDto input,RoleEnum role) {

        Optional<Role> optionalRole = roleRepository.findByName(role);

        if (optionalRole.isEmpty()) {
            throw  new NoSuchElementException("Role: "+RoleEnum.ROLE_USER+" not found");
        }

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(optionalRole.get());

        var user = new User()
                .setName(input.getName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRoles(roleSet);

        userRepository.save(user);

        return mapper.map(user, UserResponseDto.class);
    }




    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
