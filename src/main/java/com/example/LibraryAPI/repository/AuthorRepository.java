package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository   extends CrudRepository<Author,Integer> {
}
