package com.example.restfulapi.web;

import com.example.restfulapi.models.dtos.BookDTO;
import com.example.restfulapi.models.entities.Book;
import com.example.restfulapi.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {
        Optional<BookDTO> book = bookService.getBookById(bookId);

        if (book.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(book.get());
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookDTO> deleteBookById(@PathVariable("id") Long bookId) {
        bookService.deleteBookById(bookId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO newBook,
                                              UriComponentsBuilder uriComponentsBuilder) {
        Long id = bookService.creteBook(newBook);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/books/{id}").build(id))
                .build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @RequestBody BookDTO bookDTO) {

        BookDTO updatedBookDTO = this.bookService.persistBook(id, bookDTO);

        if(updatedBookDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBookDTO);
    }
}
