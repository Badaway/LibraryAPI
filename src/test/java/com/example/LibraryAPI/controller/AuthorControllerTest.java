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
class AuthorControllerTest {

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
    void getAllAuthors() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/author/authors}")
                .then()
                .statusCode(200);

    }

    @Test
    void getAuthor() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/author/1}")
                .then()
                .statusCode(200);
    }

    @Test
    void updateAuthor() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"Kareem"
                        
                        }
                        """)
                .when()
                .put("/api/author/1}")
                .then()
                .statusCode(200);

    }


    @Test
    void createAuthor() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"losy",
                                "dob":"klam",
                                "biography":"a"
                        
                        }
                        """)
                .when()
                .post("/api/author/}")
                .then()
                .statusCode(200);

    }

    @Test
    void deleteAuthor() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete("/api/author/1}")
                .then()
                .statusCode(200);
    }
}