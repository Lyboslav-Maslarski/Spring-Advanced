package com.example.errorhandling.web;

import com.example.errorhandling.model.CategoryDTO;
import com.example.errorhandling.model.ObjectNotFoundException;
import com.example.errorhandling.model.ProductDTO;
import com.example.errorhandling.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable("id") Long id, Model model) {
        CategoryDTO categoryDTO = getCategoryDTO(id);

        if (categoryDTO == null) {
            throw new ObjectNotFoundException(id);
        }

        model.addAttribute("name", categoryDTO.getCategoryName());

        return "category";
    }

    private CategoryDTO getCategoryDTO(Long id) {
        return null;
    }
}
