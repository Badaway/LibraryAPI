package com.example.LibraryAPI.Dto;

import java.util.Date;

public class UserResponseDto {
    private  String name;
    private  String email;
    private  Date createdAt;
    private  Date updatedAt;

    public String getEmail() {
        return email;
    }

    public UserResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UserResponseDto setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public UserResponseDto setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserResponseDto setName(String name) {
        this.name = name;
        return this;
    }
}
