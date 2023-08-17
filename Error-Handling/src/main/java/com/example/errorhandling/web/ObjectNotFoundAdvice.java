package com.example.errorhandling.web;

import com.example.errorhandling.model.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ObjectNotFoundAdvice {
    @ExceptionHandler({ObjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView onObjectNotFound(ObjectNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");

        modelAndView.addObject("objectId", ex.getObjectId());

        return modelAndView;
    }
}
