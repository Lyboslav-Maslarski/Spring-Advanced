package com.example.restfulapi.services;

import com.example.restfulapi.models.dtos.AuthorDTO;
import com.example.restfulapi.models.dtos.BookDTO;
import com.example.restfulapi.models.entities.Author;
import com.example.restfulapi.models.entities.Book;
import com.example.restfulapi.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
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

    public Long creteBook(BookDTO newBook) {
        String authorName = newBook.getAuthor().getName();
        Optional<Author> authorOpt = this.authorService.findAuthorByName(authorName);

        Book entityToBeSaved = new Book()
                .setTitle(newBook.getTitle())
                .setIsbn(newBook.getIsbn())
                .setAuthor(authorOpt
                        .orElseGet(() -> this.authorService
                                .saveNewAuthor(new Author().setName(authorName))));

        this.bookRepository.save(entityToBeSaved);

        return entityToBeSaved.getId();
    }

    public BookDTO persistBook(Long id, BookDTO bookDTO) {

        Optional<Book> bookOpt = this.bookRepository.findById(id);

        if (bookOpt.isEmpty()) {
            return null;
        }

        Book book = bookOpt.get();

        updateBookEntity(book, bookDTO);

        this.bookRepository.save(book);

        return this.map(book);
    }

    private void updateBookEntity(Book bookEntity, BookDTO bookDTO) {
        String title = bookDTO.getTitle();
        if (title != null) {
            bookEntity.setTitle(title);
        }

        String isbn = bookDTO.getIsbn();
        if (isbn != null) {
            bookEntity.setIsbn(isbn);
        }

        if (bookDTO.getAuthor() != null) {
            Optional<Author> authorOpt = this.authorService.findAuthorByName(bookDTO.getAuthor().getName());

            bookEntity.setAuthor(authorOpt
                    .orElseGet(() -> this.authorService
                            .saveNewAuthor(new Author().setName(bookDTO.getAuthor().getName()))));
        }
    }
}
