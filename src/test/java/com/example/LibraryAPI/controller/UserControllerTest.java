package com.example.LibraryAPI.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;

    }


    public String getToken() throws Exception {

        var token =given().contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .extract().
                path("token");


        return token.toString();

    }

    @Test
    void currentUser() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+getToken()).contentType(ContentType.JSON)
                .body("""
              {
               "name":"ahmed"
              }
          """
                )
                .when()
                .put("/api/user/me")
                .then()
                .statusCode(200);
    }


    @Test
    void getAllUsers()throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/user/users}")
                .then()
                .statusCode(200);
    }

    @Test
    void getUser() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/user/806}")
                .then()
                .statusCode(200);
    }

    @Test
    void updateUser() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+getToken()).contentType(ContentType.JSON)
                .body("""
              {
               "name":"ahmed"
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
        String token=getToken();
        given().header("Authorization","Bearer "+getToken()).contentType(ContentType.JSON)
              .when()
                .delete("/api/user/me")
                .then()
                .statusCode(200);
    }


}