package com.example.LibraryAPI.repository;

import com.example.LibraryAPI.model.Author;
import com.example.LibraryAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface BookRepository  extends JpaRepository<Book, UUID> {
    Optional<Book> findByTitle(String title);

    @Query(value = "select * from book where member_id is not null and borrow_date >(CURRENT_DATE-15) order by borrow_date;",nativeQuery = true)
    List<Book> findAllBooksShouldReturn();
    @Query(value = "select u from Book u where u.borrowDate  between ?1 and  ?2")
    List<Book> findBooksBorrowedFromTo(Date fromDate, Date toDate);
}
