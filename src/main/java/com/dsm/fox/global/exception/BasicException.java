package com.dsm.fox.global.exception;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException{
    private final String message;
    private final int code;
    public BasicException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
}
