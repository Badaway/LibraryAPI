package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(BookController.baseControllerUri)
@RestController
public class BookController {

    public static final String baseControllerUri = "/book";
    @Autowired
    private  BookService bookService;



    @GetMapping("/books")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {

        var books= bookService.getAllBooks();

        return new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping ("id/{bookId}")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable UUID bookId){

        var book=bookService.getBook(bookId);

        return new ResponseEntity<>(book, HttpStatus.OK);

    }

    @PutMapping ("/{bookId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable UUID bookId, @RequestBody BookDto book){

        var bookResponse=bookService.updateBook(book,bookId);

        return new ResponseEntity<>(bookResponse,HttpStatus.OK);

    }

    @PostMapping ("/")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<BookResponseDto> CreateBook(@Valid @RequestBody BookDto bookDto){

        var book =bookService.CreateBook(bookDto);

        return new ResponseEntity<>(book, HttpStatus.CREATED);

    }



    @DeleteMapping ("/{bookId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<String> DeleteBook( @PathVariable UUID bookId){

        bookService.deleteBook(bookId);

        return ResponseEntity.noContent().build();

    }
    @GetMapping ("{title}")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable String title){

        var book=bookService.getBook(title);

        return new ResponseEntity<>(book, HttpStatus.OK);

    }



}
