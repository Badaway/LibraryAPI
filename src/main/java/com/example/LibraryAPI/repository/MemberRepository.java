package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Optional<Member> findByEmail(String email);

    @Query(value = "SELECT * from members where id not in(select coalesce(member_id,'00000000-0000-0000-0000-c0000000000c') from book);",nativeQuery = true)
    List<Member> findAllMembersWithNoBook();
}
