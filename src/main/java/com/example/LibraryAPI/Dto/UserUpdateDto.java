package com.example.LibraryAPI.Dto;

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
public class UserUpdateDto {

    private String password;

    private String name;

}
