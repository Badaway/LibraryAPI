package com.example.LibraryAPI.Dto;

public class BookDto {
    private String Title;

    private  Long Isbn;
    private Integer AuthorId;
    private Integer MemberId;

    public String getTitle() {
        return Title;
    }

    public BookDto setTitle(String title) {
        Title = title;
        return this;
    }

    public Long getIsbn() {
        return Isbn;
    }

    public BookDto setIsbn(Long isbn) {
        Isbn = isbn;
        return this;
    }

    public Integer getAuthorId() {
        return AuthorId;
    }

    public BookDto setAuthorId(Integer authorId) {
        AuthorId = authorId;
        return this;
    }

    public Integer getMemberId() {
        return MemberId;
    }

    public BookDto setMemberId(Integer memberId) {
        MemberId = memberId;
        return this;
    }
}
