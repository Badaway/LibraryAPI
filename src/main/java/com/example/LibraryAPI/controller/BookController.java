package com.example.LibraryAPI.controller;


import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("api/book")
@RestController
public class BookController {
    private final BookService bookService;
    private final ModelMapper mapper;

    public BookController(BookService bookService, ModelMapper mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @GetMapping("/books")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Object> getAllBooks() {


        var books= bookService.getAllBooks();
        var booksResponse=new ArrayList<BookResponseDto>();
        for (Book i:books)
        {
            var book =mapper.map(i, BookResponseDto.class);
            booksResponse.add(book);
        }
        return new ResponseEntity<>(booksResponse,HttpStatus.OK);
    }
    @GetMapping ("/{bookId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Object> getBook(@PathVariable int bookId){
        var book=mapper.map(bookService.getBook(bookId),BookResponseDto.class) ;
        if(book ==null)
            return  new ResponseEntity<>("book does not exist", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(book, HttpStatus.OK);

    }

    @PutMapping ("/{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateBook(@PathVariable int bookId, @RequestBody Book book){
        var currentBook = (Book) bookService.getBook(bookId);
        if(currentBook==null)
            return  ResponseEntity.badRequest().body("book does not exist");
        if ( book.getTitle()!=null&&!book.getTitle().isEmpty() )
            currentBook.setTitle(book.getTitle());
        var updatedBook =bookService.updateBook(currentBook);
        var bookResponse =mapper.map(updatedBook,BookResponseDto.class);

        return new ResponseEntity<>(bookResponse,HttpStatus.OK);

    }

    @PostMapping ("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> CreateBook( @RequestBody BookDto bookDto){



        return bookService.newBook(bookDto);

    }

    @DeleteMapping ("/{bookId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> DeleteBook( @PathVariable int bookId){



        return bookService.deleteBook(bookId);

    }



}
