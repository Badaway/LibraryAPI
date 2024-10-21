package com.example.LibraryAPI.Dto;

public class UpdateMemberDto {
    private String Name;

    public String getName() {
        return Name;
    }

    public UpdateMemberDto setName(String name) {
        Name = name;
        return this;
    }
}
