package com.example.errorhandling.web;

import com.example.errorhandling.model.ApiErrorDTO;
import com.example.errorhandling.model.ProductDTO;
import com.example.errorhandling.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = getProductDTO(id);

        if (productDTO == null) {
            throw new ProductNotFoundException(id);
        }

        return ResponseEntity.ok(productDTO);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class})
    public @ResponseBody ApiErrorDTO onProductNotFound(ProductNotFoundException ex) {
        return new ApiErrorDTO()
                .setObjectId(ex.getId())
                .setMessage("Product was not found!");
    }

    private ProductDTO getProductDTO(Long id) {
        return null;
    }
}
