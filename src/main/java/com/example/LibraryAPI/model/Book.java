package com.example.LibraryAPI.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private Long isbn;

    @CreationTimestamp
    @Column(name = "published_date")
    private Date publishedDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    public Integer getId() {
        return id;
    }

    public Book setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Long getIsbn() {
        return isbn;
    }

    public Book setIsbn(Long isbn) {
        this.isbn = isbn;
        return this;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public Book setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
        return this;
    }

    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public Member getMember() {
        return member;
    }

    public Book setMember(Member member) {
        this.member = member;
        return this;
    }
}
