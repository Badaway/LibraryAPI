package com.example.LibraryAPI.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

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
    void getAllAuthors() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/author/authors")
                .then()
                .statusCode(200);

    }

    @Test
    void getAuthor() throws Exception {
        var email="sam@gts.com";
        var password="123";
        int authorId=6;
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/author/{authorId}",authorId)
                .then()
                .statusCode(200);
    }

    @Test
    void updateAuthor() throws Exception {
        var email="sam@gts.com";
        var password="123";
        int authorId=6;
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"Aly"
                        
                        }
                        """)
                .when()
                .put("/api/author/{authorId}",authorId)
                .then()
                .statusCode(200);

    }


    @Test
    void createAuthor() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"Rabah Mohamed",
                                "dob":"l2",
                                "biography":"To French"
                        
                        }
                        """)
                .when()
                .post("/api/author/")
                .then()
                .statusCode(201);

    }

    @Test
    void deleteAuthor() throws Exception {
        var email="sam@gts.com";
        var password="123";
        int authorId=9;
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete("/api/author/{authorId}",authorId)
                .then()
                .statusCode(200);
    }
}