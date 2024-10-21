package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.model.*;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.repository.AuthorRepository;
import com.example.LibraryAPI.repository.BookRepository;
import com.example.LibraryAPI.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, MemberRepository memberRepository)
    {
        this.bookRepository=bookRepository;
        this.authorRepository = authorRepository;
        this.memberRepository = memberRepository;
    }

    public Book getBook(int id){

        Optional<Book> bookOptional =bookRepository.findById(id);


        return bookOptional.orElse(null);
    }

    public List<Book> getAllBooks(){

        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);



        return  books;
    }

    public ResponseEntity<Object> newBook(BookDto bookDto) {

        Optional<Author> optionalAuthor;
        if (bookDto.getAuthorId() != null) {
            optionalAuthor = authorRepository.findById(bookDto.getAuthorId());
            if (optionalAuthor.isEmpty()) {
                return  new ResponseEntity<>("Not valid author id", HttpStatus.BAD_REQUEST);
            }
        }
        else
        {
            return  new ResponseEntity<>("author does not exist", HttpStatus.BAD_REQUEST);
        }
        Optional<Member> optionalMember;
        if (bookDto.getMemberId() != null) {
            optionalMember = memberRepository.findById(bookDto.getMemberId());

            Book book;
            if (optionalMember.isEmpty()) {
                book = new Book()
                        .setTitle(bookDto.getTitle())
                        .setIsbn(bookDto.getIsbn())
                        .setAuthor(optionalAuthor.get());
            }
            else {
                book = new Book()
                        .setTitle(bookDto.getTitle())
                        .setIsbn(bookDto.getIsbn())
                        .setMember(optionalMember.get())
                        .setAuthor(optionalAuthor.get());

            }
            bookRepository.save(book);
            return new ResponseEntity<>(book, HttpStatus.CREATED);

        }
        var book = new Book()
                .setTitle(bookDto.getTitle())
                .setIsbn(bookDto.getIsbn())
                .setAuthor(optionalAuthor.get());
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteBook(int id){

        bookRepository.deleteById(id);

        return  new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    public Book updateBook(Book book){

         return bookRepository.save(book);

    }
}
