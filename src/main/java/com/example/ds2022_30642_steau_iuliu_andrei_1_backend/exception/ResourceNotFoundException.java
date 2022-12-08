package com.example.ds2022_30642_steau_iuliu_andrei_1_backend.exception;

import com.example.ds2022_30642_steau_iuliu_andrei_1_backend.handlers.exceptions.model.CustomException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class ResourceNotFoundException extends CustomException {
    private static final String MESSAGE = "Resource not found!";
    private static final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public ResourceNotFoundException(String resource) {
        super(MESSAGE,httpStatus, resource, new ArrayList<>());
    }
}
