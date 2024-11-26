package com.example.LibraryAPI.Dto;

import com.example.LibraryAPI.model.Book;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class BookResponseDto {

    private String title;

    private  Long isbn;
    private String author;
    private String member;
    private UUID id;


}
