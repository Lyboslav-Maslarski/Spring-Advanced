package com.example.resttemplete;

import com.example.resttemplete.model.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateDemo implements CommandLineRunner {
    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateDemo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        ResponseEntity<BookDTO[]> allBooks = restTemplate
                .getForEntity("http://localhost:8080/api/books", BookDTO[].class);

        if (allBooks.getBody() != null) {
            for (BookDTO bookDTO : allBooks.getBody()) {
                System.out.println(bookDTO);
            }
        }
    }
}
