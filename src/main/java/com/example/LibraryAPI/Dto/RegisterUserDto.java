package com.example.LibraryAPI.Dto;

import co.elastic.clients.elasticsearch.watcher.Email;

public class RegisterUserDto {
    private String email;

    private String password;

    private String Name;

    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return Name;
    }

    public RegisterUserDto setName(String name) {
        this.Name = name;
        return this;
    }
}
