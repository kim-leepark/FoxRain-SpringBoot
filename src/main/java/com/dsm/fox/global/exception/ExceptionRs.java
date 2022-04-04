package com.dsm.fox.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionRs {
    private final int status;
    private final String message;
}
