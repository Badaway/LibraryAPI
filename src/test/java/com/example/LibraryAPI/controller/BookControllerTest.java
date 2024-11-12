package com.example.LibraryAPI.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

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
    void getAllBooks() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/book/books")
                .then()
                .statusCode(200);
    }

    @Test
    void getBook() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        int bookId=5;
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/book/{bookId}",bookId)
                .then()
                .statusCode(200);

    }

    @Test
    void updateBook() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        int bookId=1;
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                        "title":"SQL in one days"
                        }
                        """)
                .when()
                .put("/api/book/{bookId}",bookId)
                .then()
                .statusCode(200);
    }

    @Test
    void createBook() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                                             "title": "Nothing part 23",
                                             "isbn": "897676875645",
                                             "authorId": "2"
                          }
                        """)
                .when()
                .post("/api/book/")
                .then()
                .statusCode(201);
    }

    @Test
    void deleteBook() throws Exception {
        var email="sam@gts.com";
        var password="123";
        int bookId=14;
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete("/api/book/{bookId}",bookId)
                .then()
                .statusCode(200);
    }
}