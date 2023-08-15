package com.example.springsecurity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages")
public class PagesControllers {
    @GetMapping("/all")
    public String all() {
        return "all";
    }

    @GetMapping("/moderators")
    public String moderators() {
        return "moderators";
    }

    @GetMapping("/admins")
    public String admins() {
        return "admins";
    }
}
