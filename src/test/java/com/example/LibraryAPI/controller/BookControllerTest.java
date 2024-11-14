package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.repository.AuthorRepository;
import com.example.LibraryAPI.repository.BookRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.Assert;

import java.util.UUID;


import static com.example.LibraryAPI.exceptions.ExceptionMessage.bookExist;
import static common.Common.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;
    


    @BeforeEach
    public void setUp() throws Exception {
        RestAssured.port = port;

    }


    

    @Test
    void testGetAllBooks_expectedBooksReturned() throws Exception {
        String token= getToken(userEmail, userPassword);
        var books=bookRepository.findAll();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+BookController.baseControllerUri+BookController.getAllBooksUri)
                .then()
                .statusCode(200)
                .body("size()",equalTo(books.size()));
    }

    @Test
    void testGetBook_whenIdIsGiven_expectedBookReturned() throws Exception {
        String token= getToken(userEmail, userPassword);
        var book=bookRepository.findAll().get(0);
        UUID bookId= book.getId();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .get(baseUri+BookController.baseControllerUri+BookController.getBookByIdUri,bookId)
                .then()
                .statusCode(200)
                .body("title",equalTo(book.getTitle()));

    }

    @Test
    void testUpdateBook_whenIdIsGiven_expectedBookUpdated() throws Exception {
        String token= getToken(userEmail, userPassword);
        var updateBook=new BookDto().setTitle(getRandomString());
        var book=bookRepository.findAll().get(0);
        UUID bookId= book.getId();
        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(updateBook)
                .when()
                .put(baseUri+BookController.baseControllerUri+BookController.updateBookUri,bookId)
                .then()
                .statusCode(200)
                .body("title",equalTo(updateBook.getTitle()));
    }

    @Test
    void testCreateBook_whenBookIsGiven_expectedBookCreated() throws Exception {
        String token= getToken(userEmail, userPassword);


        var book= new BookDto()
                .setTitle(getRandomString())
                .setIsbn(getRandomNumber())
                .setAuthorId(defaultAuthorId);

        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .body(book)
                .when()
                .post(baseUri+BookController.baseControllerUri+BookController.createBookUri)
                .then()
                .statusCode(201)
                .body("title",equalTo(book.getTitle()));
    }

    @Test
    void testDeleteBook_whenIdIsGiven_expectedBookDeleted() throws Exception {
        var books =bookRepository.findAll();
        UUID bookId= books.get(books.size()-1).getId();
        String token= getToken(userEmail, userPassword);

        given().header("Authorization","Bearer "+token).contentType(ContentType.JSON)
                .when()
                .delete(baseUri+BookController.baseControllerUri+BookController.deleteBookUri,bookId)
                .then()
                .statusCode(204)
                ;
        boolean checkIfExist=!bookRepository.existsById(bookId);
        Assert.isTrue(checkIfExist,bookExist);


    }
}