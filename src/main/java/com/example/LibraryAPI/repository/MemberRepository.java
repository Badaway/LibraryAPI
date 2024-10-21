package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Integer> {
}
