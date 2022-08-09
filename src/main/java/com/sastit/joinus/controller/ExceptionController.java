package com.sastit.joinus.controller;

import com.sastit.joinus.model.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return new Response("error", e.getMessage(), 50000, null);
    }
}
