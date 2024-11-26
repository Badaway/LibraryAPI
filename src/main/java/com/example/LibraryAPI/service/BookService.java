package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
import com.example.LibraryAPI.exceptions.ExceptionMessage;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.AuditLog;
import com.example.LibraryAPI.model.Book;
import com.example.LibraryAPI.repository.AuthorRepository;
import com.example.LibraryAPI.repository.BookRepository;
import com.example.LibraryAPI.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private AuditService auditService;


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

        var bookToCreate =bookRepository.save(book);

        var log= new AuditLog()
                .setAction("Create")
                .setEntityId(bookToCreate.getId().toString())
                .setEntity(bookToCreate.getClass().getSimpleName())
                .setNewValue(bookToCreate.toString());
        auditService.createLog(log);


        return   bookToCreate;
          
          //return mapper.map(book, BookResponseDto.class);

    }

    public void deleteBook(UUID id){

        
            var book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException(bookNotFoundById + id));
            book.setAuthor(null);
            book.setMember(null);
            bookRepository.save(book);

        var log =new AuditLog()
                .setAction("Delete")
                .setEntityId(book.getId().toString())
                .setEntity(book.getClass().getSimpleName())
                .setOldValue(book.toString());
        auditService.createLog(log);
            bookRepository.deleteById(id);
            
    }
    public Book updateBook(BookDto book,UUID id){

        var returnedBook= bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException(bookNotFoundById + id));


        var log = new AuditLog()
                .setEntityId(returnedBook.getId().toString())
                .setOldValue(returnedBook.toString())
                .setAction("Update")
                .setEntity(returnedBook.getClass().getSimpleName())
                .setNewValue(book.toString());
        auditService.createLog(log);
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

        var log = new AuditLog()
                .setEntityId(book.getId().toString())
                .setAction("BorrowBook")
                .setEntity(book.getClass().getSimpleName())
                .setNewValue(member.toString());
        auditService.createLog(log);

        book.setMember(member);
        book.setBorrowDate(new Date());

       return bookRepository.save(book);


      // return mapper.map(book,BookResponseDto.class);



    }

    public Book checkOutBook(UUID memberId, UUID bookId) {

        var member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(memberNotFoundById + memberId));

        var book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException(bookNotFoundById + bookId));

        if(book.getMember().getId()!=memberId)
        {
            throw new NoSuchElementException(memberNotFoundById + memberId);
        }

        var log = new AuditLog()
                .setEntityId(book.getId().toString())
                .setAction("checkOutBook")
                .setEntity(book.getClass().getSimpleName())
                .setOldValue(member.toString());
        auditService.createLog(log);

        book.setMember(null);
        book.setBorrowDate(null);

        return bookRepository.save(book);

       // return mapper.map(book,BookResponseDto.class);
    }
    public Book getBook(String title){

        return  bookRepository.findByTitle(title).orElseThrow(() -> new NoSuchElementException(bookNotFoundByTitle + title));


        //return mapper.map(book,BookResponseDto.class);
    }

    public List<Book> getBooksShouldReturn(){

        return  bookRepository.findAllBooksShouldReturn();


        //return mapper.map(book,BookResponseDto.class);
    }

    public List<Book> getBooksBorrowedFromTo(String fromDate,String toDate) throws ParseException {

        var from =convertStringToDate(fromDate);
        var to =convertStringToDate(toDate);

        return bookRepository.findBooksBorrowedFromTo( from, to);
    }
    private   Date convertStringToDate(String stringDate) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date date = formatter.parse(stringDate);


       return new Timestamp(date.getTime()) ;


    }
}
