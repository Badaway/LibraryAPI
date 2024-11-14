package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    @Query(value="SELECT * from users where id in(select user_id from user_roles where role_id=3);",nativeQuery = true)
    List<User> findAllWithUserRole();
}