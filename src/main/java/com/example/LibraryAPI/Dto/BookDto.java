package com.example.LibraryAPI.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Data
@Accessors(chain = true)
public class BookDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private  Long isbn;
    @NotEmpty
    private UUID authorId;

    private UUID memberId;
    private Date borrowDate;


}
