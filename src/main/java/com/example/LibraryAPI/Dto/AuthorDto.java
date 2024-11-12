package com.example.LibraryAPI.Dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Data
@Accessors(chain = true)
public class AuthorDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String dob;

    @NotEmpty
    private String biography;


}
