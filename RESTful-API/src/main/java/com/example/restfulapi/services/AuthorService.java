package com.example.restfulapi.services;

import com.example.restfulapi.models.entities.Author;
import com.example.restfulapi.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> findAuthorByName(String name) {
        return this.authorRepository.findByName(name);
    }

    public Author saveNewAuthor(Author newAuthor) {
        return this.authorRepository.save(newAuthor);
    }
}
