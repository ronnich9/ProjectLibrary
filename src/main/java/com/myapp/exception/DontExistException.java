package com.myapp.exception;

import lombok.Getter;
import lombok.Setter;

public class DontExistException extends RuntimeException {

    @Getter
    @Setter
    private String message;

    public DontExistException(String message) {
        super(message);
        this.message = message;
    }
}
