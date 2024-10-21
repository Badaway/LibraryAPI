package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.LoginUserDto;
import com.example.LibraryAPI.Dto.RegisterUserDto;
import com.example.LibraryAPI.model.Role;
import com.example.LibraryAPI.model.RoleEnum;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.repository.RoleRepository;
import com.example.LibraryAPI.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository=roleRepository;
    }

    public User signup(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ROLE_USER);

        if (optionalRole.isEmpty()) {
            return null;
        }
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(optionalRole.get());

        var user = new User()
                .setName(input.getName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRoles(roleSet);



        return userRepository.save(user);
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
