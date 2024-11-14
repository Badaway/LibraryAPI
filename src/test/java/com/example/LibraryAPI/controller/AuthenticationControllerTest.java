package com.example.LibraryAPI.controller;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.example.LibraryAPI.Dto.*;


import static common.Common.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class AuthenticationControllerTest {

    @LocalServerPort
    private int port;





    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;
    }
    @Test
    void testRegisterUser_whenUserIsGiven_expectedUserRegistered() {

        var userRegister= new RegisterUserDto()
                .setEmail(getRandomString()+defaultEmail)//"ahsgfckslaj"
                .setName(getRandomString())
                .setPassword(defaultPassword);

        given().contentType(ContentType.JSON)
                .body(userRegister)
                .when()
                .post(baseUri +AuthenticationController.baseControllerUri+AuthenticationController.registerUri)
                .then()
                .statusCode(201)
                .body("name", equalTo(userRegister.getName()));

    }


    @Test
    void testAuthenticate_WhenUserLoginIsGiven_ExpectedTokenReturned() {

        var userLogin= new LoginUserDto()
                .setEmail(userEmail)
                .setPassword(userPassword);

        given().contentType(ContentType.JSON)
                .body(userLogin)
                .when()
                .post(baseUri +AuthenticationController.baseControllerUri+AuthenticationController.loginUri)
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("expiresIn", equalTo(1200000));
    }
}