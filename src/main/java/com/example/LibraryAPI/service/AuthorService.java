package com.example.LibraryAPI.service;

import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository)
    {
        this.authorRepository=authorRepository;
    }

    public Author getAuthor(int id){

        Optional<Author> authorOptional =authorRepository.findById(id);


        return authorOptional.orElse(null);
    }

    public ResponseEntity<Object> getAllAuthors(){

        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);



        if (!authors.isEmpty())
            return new ResponseEntity<>( authors, HttpStatus.OK);
        return new ResponseEntity<>( "NA ", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> newAuthor(Author author){
        authorRepository.save(author);
        return  new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> deleteAuthor(int id){

        authorRepository.deleteById(id);

        return  new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    public ResponseEntity<Object> updateAuthor(Author author){
        authorRepository.save(author);
        return  new ResponseEntity<>(author, HttpStatus.OK);
    }
}
