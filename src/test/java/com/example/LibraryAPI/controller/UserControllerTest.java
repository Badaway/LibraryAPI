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

        Response response =given().contentType(ContentType.JSON)
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
                .extract()
                .response();
        var body = response.body().toString();
        JSONObject respJson = new JSONObject(body.substring(body.indexOf("{")+1, body.lastIndexOf("}") + 1));

        return respJson.getString("token");

    }

    @Test
    void currentUser() throws Exception {
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
    void getAllUsers() {
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }


}