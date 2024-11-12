package com.example.LibraryAPI.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Data
@Accessors(chain = true)
public class UserResponseDto {

    private  String name;
    private  String email;
    private  Date createdAt;
    private  Date updatedAt;


}
