package com.example.LibraryAPI.Dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class BookReaderDto {


    private String title;


    private String memberName;

    private Date borrowDate;

    private  String memberEmail;

    private  long isbn;

}
