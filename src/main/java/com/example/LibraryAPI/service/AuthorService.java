package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.AuthorDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AuthorService {

    @Autowired
    private  AuthorRepository authorRepository;

    @Autowired
    private Mapper mapper;

    public AuthorDto getAuthor(UUID id){

        var author =authorRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Author not found with id " + id));

        return mapper.map(author, AuthorDto.class);
    }

    public List<AuthorDto> getAllAuthors(){

        var authors=authorRepository.findAll();

        var authorsResponse =new ArrayList<AuthorDto>();

        for (Author author:authors)
        {
            var authorResponseDto =mapper.map(author, AuthorDto.class);
            authorsResponse.add(authorResponseDto);
        }

        return  authorsResponse;


    }

    public AuthorDto CreateAuthor(AuthorDto author){

        var authorToSave = mapper.map(author,Author.class);

        authorRepository.save(authorToSave);

        return  mapper.map(author, AuthorDto.class);
    }

    public void deleteAuthor(UUID id){

        if(authorRepository.existsById(id)) {

            authorRepository.deleteById(id);

        }

        throw  new NoSuchElementException("Author with id: "+id+" not found");
    }
    public AuthorDto updateAuthor(AuthorDto author, UUID id){

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


        var savedAuthor =authorRepository.save(returnedAuthor);

        return mapper.map(savedAuthor, AuthorDto.class);
    }

    public AuthorDto getAuthor(String name){

        var author =authorRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Author not found with name " + name));

        return mapper.map(author, AuthorDto.class);
    }
}
