package com.example.restfulapi.models.dtos;

public class AuthorDTO {
    private String name;

    public AuthorDTO() {
    }

    public String getName() {
        return name;
    }

    public AuthorDTO setName(String name) {
        this.name = name;
        return this;
    }
}
