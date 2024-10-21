package com.example.LibraryAPI.controller;

import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/author")
@RestController
public class AuthorController {

    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Object> getAllAuthors() {


        return authorService.getAllAuthors();
    }
    @GetMapping ("/{authorId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<Object> getAuthor(@PathVariable int authorId){
        var author=authorService.getAuthor(authorId);
        if(author ==null)
            return  new ResponseEntity<>("author does not exist", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(author, HttpStatus.OK);

    }

    @PutMapping("/{authorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateAuthor(@PathVariable int authorId, @RequestBody Author author){
        var currentAuthor = (Author) authorService.getAuthor(authorId);
        if(currentAuthor==null)
            return  ResponseEntity.badRequest().body("author does not exist");
        if (author.getName()!=null && !author.getName().isEmpty() )
            currentAuthor.setName(author.getName());


        return authorService.updateAuthor(currentAuthor);

    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> CreateAuthor( @RequestBody Author author){



        return authorService.newAuthor(author);

    }

    @DeleteMapping ("/{authorId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> DeleteAuthor( @PathVariable int authorId){



        return authorService.deleteAuthor(authorId);

    }
}
