package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping(BookController.baseControllerUri)
@RestController
public class BookController {

    public static final String baseControllerUri = "/book";

    public static final String getAllBooksUri = "/";
    public static final String getBookByIdUri = "/id/{bookId}";
    public static final String updateBookUri = "/{bookId}";
    public static final String createBookUri = "/";
    public static final String deleteBookUri = "/{bookId}";
    public static final String getBookByTitleUri = "/{title}";



    @Autowired
    private  BookService bookService;
    @Autowired
    private Mapper mapper;



    @GetMapping(getAllBooksUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {

        var books= bookService.getAllBooks();
        var booksResponse =new ArrayList<BookResponseDto>();

        for (Book book:books)
        {
            var bookResponseDto =mapper.map(book, BookResponseDto.class);
            booksResponse.add(bookResponseDto);
        }

        return new ResponseEntity<>(booksResponse,HttpStatus.OK);
    }
    @GetMapping (getBookByIdUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable UUID bookId){

        var book=bookService.getBook(bookId);

        var bookResponse=mapper.map(book,BookResponseDto.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);

    }

    @PutMapping (updateBookUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable UUID bookId, @RequestBody BookDto book){

        var updateBook=bookService.updateBook(book,bookId);

        var bookResponse=mapper.map(updateBook,BookResponseDto.class);

        return new ResponseEntity<>(bookResponse,HttpStatus.OK);

    }

    @PostMapping (createBookUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BookDto bookDto){

        var book =bookService.CreateBook(bookDto);

        var bookResponse=mapper.map(book,BookResponseDto.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);

    }



    @DeleteMapping (deleteBookUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<String> deleteBook( @PathVariable UUID bookId){

        bookService.deleteBook(bookId);

        return ResponseEntity.noContent().build();

    }
    @GetMapping (getBookByTitleUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable String title){

        var book=bookService.getBook(title);

        var bookResponse=mapper.map(book,BookResponseDto.class);

        return new ResponseEntity<>(bookResponse, HttpStatus.OK);

    }



}
