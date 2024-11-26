package com.example.LibraryAPI.service;

import com.example.LibraryAPI.Dto.AuthorDto;
import com.example.LibraryAPI.mapping.Mapper;
import com.example.LibraryAPI.model.AuditLog;
import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.repository.AuditLogRepository;
import com.example.LibraryAPI.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


import static com.example.LibraryAPI.exceptions.ExceptionMessage.authorNotFoundById;
import static com.example.LibraryAPI.exceptions.ExceptionMessage.authorNotFoundByName;
import static com.example.LibraryAPI.service.AuditService.getUsername;

@Service
public class AuthorService {

    @Autowired
    private  AuthorRepository authorRepository;

    @Autowired
    AuditService auditService;

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

    @Transactional
    public Author createAuthor(AuthorDto author){

        var authorToSave = mapper.map(author,Author.class);
        var authorToCreate = authorRepository.save(authorToSave);
        var log= new AuditLog()
                .setAction("Create")
                .setEntityId(authorToCreate.getId().toString())
                .setEntity(authorToCreate.getClass().getSimpleName())
                .setNewValue(authorToCreate.toString());
        auditService.createLog(log);

       return authorToCreate;

//        return  mapper.map(author, AuthorDto.class);
    }

    @Transactional
    public void deleteAuthor(UUID id){

        if(authorRepository.existsById(id)) {

            var authorToDelete =authorRepository.findById(id).orElseThrow();
            var log =new AuditLog()
                    .setAction("Delete")
                    .setEntityId(authorToDelete.getId().toString())
                    .setEntity(authorToDelete.getClass().getSimpleName())
                    .setOldValue(authorToDelete.toString());
            auditService.createLog(log);
            authorRepository.deleteById(id);



        }

        else {
        throw  new NoSuchElementException(authorNotFoundById+id);
        }
    }

    @Transactional
    public Author updateAuthor(AuthorDto author, UUID id){

        var returnedAuthor= authorRepository.findById(id).orElseThrow();
        var log = new AuditLog()
                .setEntityId(returnedAuthor.getId().toString())
                .setOldValue(returnedAuthor.toString())
                .setAction("Update")
                .setEntity(returnedAuthor.getClass().getSimpleName())
                .setNewValue(author.toString());
        auditService.createLog(log);
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
