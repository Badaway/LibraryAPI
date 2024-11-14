package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.AuthorDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping(AuthorController.baseControllerUri)
@RestController
public class AuthorController {

    public static final String baseControllerUri = "/author";

    public static final String getAllAuthorsUri = "/";
    public static final String getAuthorByIdUri = "/id/{authorId}";
    public static final String updateAuthorUri = "/{authorId}";
    public static final String createAuthorUri = "/";
    public static final String deleteAuthorUri = "/{authorId}";
    public static final String getAuthorByNameUri = "/{authorName}";

    @Autowired
    private  AuthorService authorService;
    @Autowired
    private Mapper mapper;




    @GetMapping(getAllAuthorsUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {

        var authors=authorService.getAllAuthors();
        var authorsResponse =new ArrayList<AuthorDto>();

        for (Author author:authors)
        {
            var authorResponseDto =mapper.map(author, AuthorDto.class);
            authorsResponse.add(authorResponseDto);
        }



        return new ResponseEntity<>(authorsResponse, HttpStatus.OK);
    }


    @GetMapping (getAuthorByIdUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable UUID authorId){

        var author=authorService.getAuthor(authorId);

        var authorResponse =mapper.map(author, AuthorDto.class);

        return new ResponseEntity<>(authorResponse, HttpStatus.OK);

    }

    @PutMapping(updateAuthorUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable UUID authorId, @RequestBody AuthorDto author){


        var returnedAuthor= authorService.updateAuthor(author,authorId);

        var authorResponse =mapper.map(returnedAuthor, AuthorDto.class);



        return new ResponseEntity<>(authorResponse, HttpStatus.OK);

    }

    @PostMapping(createAuthorUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorDto author){

        var returnedAuthor=authorService.createAuthor(author);

        var authorResponse =mapper.map(returnedAuthor, AuthorDto.class);

        return new ResponseEntity<>(authorResponse,HttpStatus.CREATED);

    }

    @DeleteMapping (deleteAuthorUri)
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<String> deleteAuthor(@PathVariable UUID authorId){

        authorService.deleteAuthor(authorId);


        return ResponseEntity.noContent().build();

    }

    @GetMapping (getAuthorByNameUri)
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable String authorName){

        var author=authorService.getAuthor(authorName);

        var authorResponse =mapper.map(author, AuthorDto.class);

        return new ResponseEntity<>(authorResponse, HttpStatus.OK);

    }
}
