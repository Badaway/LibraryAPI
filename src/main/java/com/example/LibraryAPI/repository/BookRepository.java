package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository  extends CrudRepository<Book,Integer> {
}
