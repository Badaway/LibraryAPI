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
class MemberControllerTest {

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
    void getAllMembers() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/member/members}")
                .then()
                .statusCode(200);
    }

    @Test
    void getMember() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get("/api/member/3}")
                .then()
                .statusCode(200);
    }

    @Test
    void updateMember() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"Hatem"
                        
                        }
                        """)
                .when()
                .put("/api/member/3}")
                .then()
                .statusCode(200);
    }

    @Test
    void createMember() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body("""
                        {
                            "name":"sami",
                            "email":"sami@gts.co"
                        
                        }
                        """)
                .when()
                .post("/api/member/3}")
                .then()
                .statusCode(200);
    }

    @Test
    void deleteMember() throws Exception {
        String token=getToken();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete("/api/member/3}")
                .then()
                .statusCode(200);
    }
}