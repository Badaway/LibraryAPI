package com.example.LibraryAPI.Dto;

public class BookResponseDto {

    private String Title;

    private  Long Isbn;
    private String Author;
    private String Member;

    public String getMember() {
        return Member;
    }

    public BookResponseDto setMember(String member) {
        Member = member;
        return this;
    }

    public String getAuthor() {
        return Author;
    }

    public BookResponseDto setAuthor(String author) {
        Author = author;
        return this;
    }

    public Long getIsbn() {
        return Isbn;
    }

    public BookResponseDto setIsbn(Long isbn) {
        Isbn = isbn;
        return this;
    }

    public String getTitle() {
        return Title;
    }

    public BookResponseDto setTitle(String title) {
        Title = title;
        return this;
    }



}
