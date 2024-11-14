package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.AuthorDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.example.LibraryAPI.exceptions.ExceptionMessage.authorNotFoundById;
import static com.example.LibraryAPI.exceptions.ExceptionMessage.authorNotFoundByName;

@Service
public class AuthorService {

    @Autowired
    private  AuthorRepository authorRepository;

    @Autowired
    private Mapper mapper;

    public Author getAuthor(UUID id){

        return authorRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException(authorNotFoundById + id));

//        return mapper.map(author, AuthorDto.class);
    }

    public List<Author> getAllAuthors(){

        return authorRepository.findAll();




    }

    public Author createAuthor(AuthorDto author){

        var authorToSave = mapper.map(author,Author.class);

       return authorRepository.save(authorToSave);

//        return  mapper.map(author, AuthorDto.class);
    }

    public void deleteAuthor(UUID id){

        if(authorRepository.existsById(id)) {

            authorRepository.deleteById(id);

        }

        else {
        throw  new NoSuchElementException(authorNotFoundById+id);
        }
    }
    public Author updateAuthor(AuthorDto author, UUID id){

        var returnedAuthor= authorRepository.findById(id).orElseThrow();

        if(author.getName()!=null && !author.getName().isEmpty()){
            returnedAuthor.setName(author.getName());
        }

        if(author.getDob()!=null && !author.getDob().isEmpty()){
            returnedAuthor.setName(author.getDob());
        }

        if(author.getBiography()!=null && !author.getBiography().isEmpty()){
            returnedAuthor.setName(author.getName());
        }


        return authorRepository.save(returnedAuthor);

//        return mapper.map(savedAuthor, AuthorDto.class);
    }

    public Author getAuthor(String name){

        return authorRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException(authorNotFoundByName+ name));

//        return mapper.map(author, AuthorDto.class);
    }
}
