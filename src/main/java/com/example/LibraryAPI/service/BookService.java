package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.exceptions.ExceptionMessage;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.repository.AuthorRepository;
import com.example.LibraryAPI.repository.BookRepository;
import com.example.LibraryAPI.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.example.LibraryAPI.exceptions.ExceptionMessage.*;

@Service
public class BookService {
    @Autowired
    private  BookRepository bookRepository;
    @Autowired
    private  MemberRepository memberRepository;
    @Autowired
    private Mapper mapper;
    @Autowired
    private AuthorRepository authorRepository;


    public Book getBook(UUID id){

        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found with id " + id));


        //return mapper.map(book,BookResponseDto.class);
    }

    public List<Book> getAllBooks(){

        return bookRepository.findAll();


    }

    public Book CreateBook(BookDto bookDto) {

        var authorId=bookDto.getAuthorId();
        var author = authorRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException(ExceptionMessage.authorNotFoundById + authorId));

        var book = new Book()
                .setTitle(bookDto.getTitle())
                .setIsbn(bookDto.getIsbn())
                .setAuthor(author);

        return   bookRepository.save(book);
          
          //return mapper.map(book, BookResponseDto.class);

    }

    public void deleteBook(UUID id){

        
            var book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException(bookNotFoundById + id));
            book.setAuthor(null);
            book.setMember(null);
            bookRepository.save(book);
            bookRepository.deleteById(id);
            
    }
    public Book updateBook(BookDto book,UUID id){

        var returnedBook= bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException(bookNotFoundById + id));

        if(book.getTitle()!=null && !book.getTitle().isEmpty()){
            returnedBook.setTitle(book.getTitle());
        }

        if(book.getIsbn()!=null ){
            returnedBook.setIsbn(book.getIsbn());
        }

        return bookRepository.save(returnedBook);

       // return mapper.map(savedBook, BookResponseDto.class);

    }
    public Book borrowBook(UUID memberId,UUID  bookId){


        var member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(memberNotFoundById+ memberId));

        var book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException(bookNotFoundById + bookId));

        if(book.getMember()!=null){
            throw  new RuntimeException(bookCanNotBorrowed);
        }

        book.setMember(member);

       return bookRepository.save(book);


      // return mapper.map(book,BookResponseDto.class);



    }

    public Book checkOutBook(UUID memberId, UUID bookId) {

        var member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(memberNotFoundById + memberId));

        var book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException(bookNotFoundById + bookId));

        book.setMember(member);

        return bookRepository.save(book);

       // return mapper.map(book,BookResponseDto.class);
    }
    public Book getBook(String title){

        return  bookRepository.findByTitle(title).orElseThrow(() -> new NoSuchElementException(bookNotFoundByTitle + title));


        //return mapper.map(book,BookResponseDto.class);
    }
}
