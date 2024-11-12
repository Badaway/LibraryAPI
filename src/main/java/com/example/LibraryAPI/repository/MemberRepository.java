package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Author> findByEmail(String email);
}
