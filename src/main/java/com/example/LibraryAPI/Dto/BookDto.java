package com.example.LibraryAPI.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Data
@Accessors(chain = true)
@ToString
public class BookDto {


    private String title;


    private  Long isbn;

    private UUID authorId;

    private UUID memberId;
    private Date borrowDate;

    private UUID id;


}
