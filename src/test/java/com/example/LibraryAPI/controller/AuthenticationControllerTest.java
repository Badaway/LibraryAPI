package com.example.LibraryAPI.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class AuthenticationControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;
    }
    @Test
    void register() {
        var username="Kareem";
        var password="123";
        var email="kare@gts.co";
        given().contentType(ContentType.JSON)
                .body("""
              {
                "email": "karem@gts.co",
                "name":"Karem",
                "password": "123456"
              }
          """
                )
                .when()
                .post("/api/auth/signup")
                .then()
                .statusCode(201)
                .body("email", equalTo("karem@gts.co"))
                .body("name", equalTo("Karem"));

    }


    @Test
    void authenticate() {
        given().contentType(ContentType.JSON)
                .body("""
              {
                "email": "admin.admin@email.com",
                "password": "123456"
              }
          """
                )
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .body("expiresIn", equalTo(1200000));
    }
}