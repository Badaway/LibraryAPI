package com.example.LibraryAPI.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Data
@Accessors(chain = true)
@ToString
public class MemberBorrowedBook {

    private Integer totalBooks;
    private String id;
    private String name;
    private List<BookResponseDto> borrowedBooks;

}
