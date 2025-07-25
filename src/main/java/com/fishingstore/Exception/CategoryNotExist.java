package com.fishingstore.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryNotExist extends RuntimeException {

    public CategoryNotExist(String message) {
        super(message);
    }
}
