package com.example.restfulapi.services;

import com.example.restfulapi.models.dtos.AuthorDTO;
import com.example.restfulapi.models.dtos.BookDTO;
import com.example.restfulapi.models.entities.Book;
import com.example.restfulapi.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(this::map);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private BookDTO map(Book book) {
        return new BookDTO()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setIsbn(book.getIsbn())
                .setAuthor(new AuthorDTO().setName(book.getAuthor().getName()));
    }

    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
