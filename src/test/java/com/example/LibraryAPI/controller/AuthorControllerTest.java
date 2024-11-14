package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.AuthorDto;

import com.example.LibraryAPI.repository.AuthorRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.UUID;


import static com.example.LibraryAPI.exceptions.ExceptionMessage.authorExist;
import static common.Common.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AuthorRepository authorRepository;
    
    
    


    @Value("")

    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;

    }






    @Test
    void testGetAllAuthors_ExpectedAuthorsReturned() throws Exception {
        String token= getToken(userEmail, userPassword);

        var size =authorRepository.findAll().size();

        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+AuthorController.baseControllerUri+AuthorController.getAllAuthorsUri)
                .then()
                .statusCode(200)
                .body("size()",equalTo(size));

    }

    @Test
    void testGetAuthor_whenIdIsGiven_expectedAuthorReturned() throws Exception {
        var authors=authorRepository.findAll();
        UUID authorId= authors.get(0).getId();
        var author=authorRepository.findById(authorId).orElseThrow();

        String token= getToken(userEmail, userPassword);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get( baseUri+AuthorController.baseControllerUri+AuthorController.getAuthorByIdUri,authorId)
                .then()
                .statusCode(200)
                .body("name",equalTo(author.getName()));
    }

    @Test
    void testUpdateAuthor_whenIdIsGiven_expectedAuthorUpdated() throws Exception {
        var authors=authorRepository.findAll();
        UUID authorId= authors.get(0).getId();
        String token= getToken(userEmail, userPassword);
        var updateAuthor=new AuthorDto().setName(getRandomString());


        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(updateAuthor)
                .when()
                .put(baseUri+AuthorController.baseControllerUri+AuthorController.updateAuthorUri,authorId)
                .then()
                .statusCode(200)
                .body("name",equalTo(updateAuthor.getName()));


    }


    @Test
    void testCreateAuthor_whenAuthorIsGiven_expectedAuthorCreated() throws Exception {
        String token= getToken(userEmail, userPassword);
        var createAuthor= new AuthorDto()
                .setName(getRandomString())
                .setDob(getRandomString())
                .setBiography(getRandomString());

        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(createAuthor)
                .when()
                .post(baseUri+AuthorController.baseControllerUri+AuthorController.createAuthorUri)
                .then()
                .statusCode(201)
                .body("name",equalTo(createAuthor.getName()));

    }

    @Test
    void testDeleteAuthor_whenIdIsGiven_expectedAuthorDeleted() throws Exception {
        var authors=authorRepository.findAllAuthorWithNoBook();
        UUID authorId= authors.get(authors.size()-1).getId();
        String token= getToken(userEmail, userPassword);
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete(baseUri+AuthorController.baseControllerUri+AuthorController.deleteAuthorUri,authorId)
                .then()
                .statusCode(204);
        boolean checkIfExist=!authorRepository.existsById(authorId);
        Assert.isTrue(checkIfExist,authorExist);
    }
}