package com.example.LibraryAPI.Dto;

public class UpdateUserDto {
    private String password;

    private String Name;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
