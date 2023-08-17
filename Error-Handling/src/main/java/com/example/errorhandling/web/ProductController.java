package com.example.errorhandling.web;

import com.example.errorhandling.model.ObjectNotFoundException;
import com.example.errorhandling.model.ProductDTO;
import com.example.errorhandling.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") Long productId, Model model) {
        ProductDTO productDTO = getProductDTO(productId);

//        if (productDTO == null) {
//            throw new ProductNotFoundException(productId);
//        }
        if (productDTO == null) {
            throw new ObjectNotFoundException(productId);
        }
        model.addAttribute("name", productDTO.getName());

        return "product";
    }

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    @ExceptionHandler({ProductNotFoundException.class})
//    public ModelAndView onProductNotFound(ProductNotFoundException ex) {
//        ModelAndView modelAndView = new ModelAndView("product-not-found");
//
//        modelAndView.addObject("productId", ex.getId());
//
//        return modelAndView;
//    }

    private ProductDTO getProductDTO(Long id) {
        return null;
    }

}
