package com.example.LibraryAPI.Dto;


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
public class UpdateMemberDto {
    @NotEmpty
    private String name;


}
