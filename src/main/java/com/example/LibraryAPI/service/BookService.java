package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.BookDto;
import com.example.LibraryAPI.Dto.BookResponseDto;
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


    public BookResponseDto getBook(UUID id){

        var book =bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found with id " + id));


        return mapper.map(book,BookResponseDto.class);
    }

    public List<BookResponseDto> getAllBooks(){

        var books=bookRepository.findAll();

        var booksResponse =new ArrayList<BookResponseDto>();

        for (Book book:books)
        {
            var bookResponseDto =mapper.map(book, BookResponseDto.class);
            booksResponse.add(bookResponseDto);
        }

        return  booksResponse;
    }

    public BookResponseDto CreateBook(BookDto bookDto) {

        var authorId=bookDto.getAuthorId();
        var author = authorRepository.findById(authorId).orElseThrow(() -> new NoSuchElementException("Author not found with id " + authorId));

        var book = new Book()
                .setTitle(bookDto.getTitle())
                .setIsbn(bookDto.getIsbn())
                .setAuthor(author);

          bookRepository.save(book);
          
          return mapper.map(book, BookResponseDto.class);

    }

    public void deleteBook(UUID id){

        
            var book = bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found with id " + id));
            book.setAuthor(null);
            book.setMember(null);
            bookRepository.save(book);
            bookRepository.deleteById(id);
            
    }
    public BookResponseDto updateBook(BookDto book,UUID id){

        var returnedBook= bookRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Book not found with id " + id));

        if(book.getTitle()!=null && !book.getTitle().isEmpty()){
            returnedBook.setTitle(book.getTitle());
        }

        if(book.getIsbn()!=null ){
            returnedBook.setIsbn(book.getIsbn());
        }

        var savedBook =bookRepository.save(returnedBook);

        return mapper.map(savedBook, BookResponseDto.class);

    }
    public BookResponseDto borrowBook(UUID memberId,UUID  bookId){


        var member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member not found with id " + memberId));

        var book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("Book not found with id " + bookId));

        if(book.getMember()!=null){
            throw  new RuntimeException("There is already member borrowed that book");
        }

        book.setMember(member);

        bookRepository.save(book);


       return mapper.map(book,BookResponseDto.class);



    }

    public BookResponseDto checkOutBook(UUID memberId, UUID bookId) {

        var member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException("Member not found with id " + memberId));

        var book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("Book not found with id " + bookId));

        book.setMember(member);

        bookRepository.save(book);

        return mapper.map(book,BookResponseDto.class);
    }
    public BookResponseDto getBook(String title){

        var book =bookRepository.findByTitle(title).orElseThrow(() -> new NoSuchElementException("Book not found with name " + title));


        return mapper.map(book,BookResponseDto.class);
    }
}
