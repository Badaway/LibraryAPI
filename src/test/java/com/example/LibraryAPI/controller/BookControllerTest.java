package com.example.LibraryAPI.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

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
    void getAllBooks() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/book/books}")
                .then()
                .statusCode(200);
    }

    @Test
    void getBook() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/book/1}")
                .then()
                .statusCode(200);

    }

    @Test
    void updateBook() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        "title":"SQL in 2 days"
                        """)
                .when()
                .put("/api/book/1}")
                .then()
                .statusCode(200);
    }

    @Test
    void createBook() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        "title":"Nothing part 21",
                          "isbn":"4325834925432093",
                          "authorId":"1"
                        """)
                .when()
                .put("/api/book/1}")
                .then()
                .statusCode(200);
    }

    @Test
    void deleteBook() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete("/api/book/1}")
                .then()
                .statusCode(200);
    }
}