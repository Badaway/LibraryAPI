package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository   extends JpaRepository<Author, UUID> {
    Optional<Author> findByName(String name);

    @Query(value = "SELECT * from authors where id not in(select coalesce(author_id,'00000000-0000-0000-0000-c0000000000c') from book);",nativeQuery = true)
    List<Author> findAllAuthorWithNoBook();
}
