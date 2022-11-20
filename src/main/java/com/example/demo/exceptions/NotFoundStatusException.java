package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundStatusException extends ResponseStatusException {
    private static final long serialVersionUID = 1;

    public NotFoundStatusException() {
        this(null);
    }

    public NotFoundStatusException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
