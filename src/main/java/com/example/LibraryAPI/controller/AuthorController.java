package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.Dto.AuthorDto;
import com.example.LibraryAPI.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping(AuthorController.baseControllerUri)
@RestController
public class AuthorController {

    public static final String baseControllerUri = "/author";

    @Autowired
    private  AuthorService authorService;




    @GetMapping("/authors")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<List<AuthorDto>> getAllAuthors() {

        var authors=authorService.getAllAuthors();

        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


    @GetMapping ("id/{authorId}")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable UUID authorId){

        var author=authorService.getAuthor(authorId);

        return new ResponseEntity<>(author, HttpStatus.OK);

    }

    @PutMapping("/{authorId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable UUID authorId, @RequestBody AuthorDto author){


        var authorResponse= authorService.updateAuthor(author,authorId);

        return new ResponseEntity<>(authorResponse, HttpStatus.OK);

    }

    @PostMapping("/")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<AuthorDto> CreateAuthor(@Valid @RequestBody AuthorDto author){

        var returnedAuthor=authorService.CreateAuthor(author);

        return new ResponseEntity<>(returnedAuthor,HttpStatus.CREATED);

    }

    @DeleteMapping ("/{authorId}")
    @PreAuthorize("hasRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString())")
    public ResponseEntity<String> DeleteAuthor( @PathVariable UUID authorId){

        authorService.deleteAuthor(authorId);


        return ResponseEntity.noContent().build();

    }

    @GetMapping ("/{authorName}")
    @PreAuthorize("hasAnyRole(T(com.example.LibraryAPI.enums.RoleEnum).ROLE_ADMIN.toString(),T(com.example.LibraryAPI.enums.RoleEnum).ROLE_USER.toString())")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable String authorName){

        var author=authorService.getAuthor(authorName);

        return new ResponseEntity<>(author, HttpStatus.OK);

    }
}
