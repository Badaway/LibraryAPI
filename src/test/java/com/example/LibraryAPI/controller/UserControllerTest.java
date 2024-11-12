package com.example.LibraryAPI.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;

    }




    public String getToken(String email,String password) throws Exception {



        String requestBody= "{\"email\":\""+email+"\",\"password\":\""+password+"\"}";


        var token =given().contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/auth/login")
                .then()
                .contentType(ContentType.JSON)
                .extract().
                path("token");


        return token.toString();

    }

    @Test
    void currentUser() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/user/me")
                .then()
                .statusCode(200);
    }


    @Test
    void getAllUsers()throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/user/users")
                .then()
                .statusCode(200);
    }

    @Test
    void getUser() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        int userId=805;
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/user/{userId}",userId)
                .then()
                .statusCode(200);
    }

    @Test
    void updateUser() throws Exception {
        var email="tamer_07@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
              {
                "name":"Karem"
              }
          """
                )
                .when()
                .put("/api/user/me")
                .then()
                .statusCode(200);

    }

    @Test
    void deleteUser() throws Exception {
        var email="tamer_07@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
              .when()
                .delete("/api/user/me")
                .then()
                .statusCode(200);
    }


    @Test
    void registerAdmin() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
              {
               "email": "Sam_04@gts.co",
                "name":"Sam",
                "password": "123"
              }
          """
                )
                .when()
                .post("/api/user/admin")
                .then()
                .statusCode(201);
    }
}