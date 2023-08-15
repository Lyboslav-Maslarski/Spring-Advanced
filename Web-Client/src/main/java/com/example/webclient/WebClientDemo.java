package com.example.webclient;

import com.example.webclient.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientDemo implements CommandLineRunner {
    @Override
    public void run(String... args) {
        WebClient webClient = WebClient.create("https://reqres.in/");

        User user = webClient
                .get()
                .uri("api/users/2")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class)
                .block();

        System.out.println(user);
    }
}
