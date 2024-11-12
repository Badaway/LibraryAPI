package com.example.LibraryAPI.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Data
@Accessors(chain = true)
public class UpdateUserDto {

    private String password;

    private String name;


}
