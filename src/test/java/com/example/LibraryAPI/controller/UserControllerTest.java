package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.RegisterUserDto;
import com.example.LibraryAPI.Dto.UserUpdateDto;
import com.example.LibraryAPI.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.UUID;

import static com.example.LibraryAPI.exceptions.ExceptionMessage.userExist;
import static common.Common.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;

    }




    @Test
    void testCurrentUser_whenIdIsGiven_expectedUserReturned() throws Exception {
        var user=userRepository.findAllWithUserRole().get(0);

        String token= getToken(user.getEmail(), defaultPassword);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+UserController.baseControllerUri+UserController.currentUserUri)
                .then()
                .statusCode(200)
                .body("name",equalTo(user.getName()));
    }


    @Test
    void testGetAllUsers_expectedAllUsersReturned()throws Exception {
        String token= getToken(userEmail, userPassword);
        var users=userRepository.findAll();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+UserController.baseControllerUri+UserController.getAllUsersUri)
                .then()
                .statusCode(200)
                .body("size()",equalTo(users.size()));
    }

    @Test
    void testGetUser_whenIdIsGiven_expectedReturnUser() throws Exception {
        String token= getToken(userEmail, userPassword);
        var user=userRepository.findAll().get(0);

        UUID userId=user.getId();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+UserController.baseControllerUri+UserController.getUserByIdUri,userId)
                .then()
                .statusCode(200)
                .body("name",equalTo(user.getName()));
    }

    @Test
    void testUpdateUser_whenNewNameIsGiven_expectedUserUpdated() throws Exception {
        var user=userRepository.findAllWithUserRole().get(0);

        String token= getToken(user.getEmail(), defaultPassword);

        var userUpdate=new UserUpdateDto()
                .setName(getRandomString());

        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(userUpdate)
                .when()
                .put(baseUri+UserController.baseControllerUri+UserController.updateUserUri)
                .then()
                .statusCode(200)
                .body("name",equalTo(userUpdate.getName()));

    }

    @Test
    void testDeleteUser_whenIdIsGiven_expectedUserDeleted() throws Exception {
        var user=userRepository.findAllWithUserRole().get(0);

        String token= getToken(user.getEmail(), defaultPassword);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
              .when()
                .delete(baseUri+UserController.baseControllerUri+UserController.deleteUserUri)
                .then()
                .statusCode(204);
        boolean checkIfExist=!userRepository.existsById(user.getId());
        Assert.isTrue(checkIfExist,userExist);
    }


    @Test
    void testRegisterAdmin_whenUserIsGiven_expectedUserRegistered() throws Exception {
        String token= getToken(userEmail, userPassword);
        var userRegister= new RegisterUserDto()
                .setEmail(getRandomString()+defaultEmail)
                .setName(getRandomString())
                .setPassword(defaultPassword);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(userRegister)
                .when()
                .post(baseUri+UserController.baseControllerUri+UserController.registerAdminUri)
                .then()
                .statusCode(201)
                .body("name",equalTo(userRegister.getName()));
    }
}