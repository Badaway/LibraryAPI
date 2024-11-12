package com.example.LibraryAPI.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Data
@Accessors(chain = true)
public class RegisterUserDto {

    @Email(message = "It should be valid email")
    private String email;


    private String password;

    @NotEmpty
    private String name;


}