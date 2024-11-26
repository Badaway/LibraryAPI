package com.example.LibraryAPI.Dto;

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
public class MemberResponseDto {

    private String name;

    private String email;

    private Date membershipDate;

    private UUID id;
}
