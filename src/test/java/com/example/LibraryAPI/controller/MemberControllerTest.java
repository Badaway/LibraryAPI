package com.example.LibraryAPI.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {

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
    void getAllMembers() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/member/members")
                .then()
                .statusCode(200);
    }

    @Test
    void getMember() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        int memberId=2;
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/member/{memberId}",memberId)
                .then()
                .statusCode(200);
    }

    @Test
    void updateMember() throws Exception {
        var email="sam@gts.com";
        var password="123";
        int memberId=2;
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"Hatem"
                        
                        }
                        """)
                .when()
                .put("/api/member/{memberId}",memberId)
                .then()
                .statusCode(200);
    }

    @Test
    void createMember() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"sami",
                            "email":"sami_01@gts.com"
                        
                        }
                        """)
                .when()
                .post("/api/member/")
                .then()
                .statusCode(201);
    }

    @Test
    void deleteMember() throws Exception {
        var email="sam@gts.com";
        var password="123";
        int memberId=6;
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete("/api/member/{memberId}",memberId)
                .then()
                .statusCode(200);
    }

    @Test
    void borrowBook() throws Exception {
        var email="sam@gts.com";
        var password="123";
        String token=getToken(email,password);
        int memberId=2;
        int bookId=20;
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .put("/api/member/{memberId}/borrow/{bookId}",memberId,bookId)
                .then()
                .statusCode(200);
    }

    @Test
    void unborrowBook() throws Exception {
        var email="sam@gts.com";
        var password="123";
        int memberId=2;
        int bookId=7;
        String token=getToken(email,password);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .put("/api/member/{memberId}/rm/{bookId}",memberId,bookId)
                .then()
                .statusCode(200);
    }
}