package org.tdsast.joinus.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.tdsast.joinus.model.response.Response;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e) {
        return new Response("error", e.getMessage(), 50000, null);
    }
}