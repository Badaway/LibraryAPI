package com.example.LibraryAPI.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Data
@Accessors(chain = true)
@ToString
public class LoginUserDto {
    @Email
    private String email;

    @NotEmpty
    private String password;


}
