package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Role;
import com.example.LibraryAPI.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
