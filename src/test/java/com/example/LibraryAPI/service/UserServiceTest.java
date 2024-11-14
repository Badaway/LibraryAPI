package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.UpdateUserDto;
import com.example.LibraryAPI.model.User;
import com.example.LibraryAPI.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Arrays;

import java.util.Optional;
import java.util.UUID;

import static common.Common.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;  // Mock the UserRepository dependency

    @InjectMocks
    private UserService userService;


    private User user1;
    private User user2;


    @BeforeEach
    void setUp() {
        // Setup mock data for testing
        user1 = new User().setName(getRandomString())
               .setId(UUID.fromString("48257d4d-a5a9-4d1e-9a39-32ba6bfa0b2f"))
               .setPassword(defaultPassword)
               .setEmail(getRandomString()+defaultEmail)
               ;
        user2 = new User().setName(getRandomString())
               .setId(UUID.fromString("48257d4d-a5a9-4d1e-9a39-32ba6bfa0b2f"))
               .setPassword(defaultPassword)
               .setEmail(getRandomString()+defaultEmail)
               ;
    }

    @Test
    void testGetUser_whenIdIsGiven_expectedReturnUser() {

        when(userRepository.findById(user1.getId())).thenReturn(Optional.ofNullable(user1));
        var user=userService.getUser(user1.getId());
        assertEquals(user1.getName(),user.getName() );

    }

    @Test
    void testGetAllUsers_expectedAllUsersReturned() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1,user2));

        var users=userService.getAllUsers();

        assertEquals(2, users.size());
    }

    @Test
    void testDeleteUser_whenIdIsGiven_expectedUserDeleted() {

        when(userRepository.existsById(user1.getId())).thenReturn(true);

        userService.deleteUser(user1.getId());


        verify(userRepository, times(1)).deleteById(user1.getId());

    }

    @Test
    void testUpdateUser_whenNewNameIsGiven_expectedUserUpdated() {


        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));

        var updatedUser=new UpdateUserDto().setName(getRandomString());
        user1.setName(updatedUser.getName());

        when(userRepository.save(user1)).thenReturn(user1);


        var user = userService.updateUser( updatedUser,user1.getId());


        verify(userRepository, times(1)).save(user1);


        assertNotNull(user);
        assertEquals(updatedUser.getName(), user.getName());
    }


}