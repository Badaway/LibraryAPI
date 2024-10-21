package com.example.LibraryAPI.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String dob;

    @Column
    private String biography;

    @CreationTimestamp
    @Column( name = "created_at")
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public Author setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public Author setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public String getBiography() {
        return biography;
    }

    public Author setBiography(String biography) {
        this.biography = biography;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Author setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
