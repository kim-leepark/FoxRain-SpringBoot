package com.dsm.fox.global.exception.exceptions;

import com.dsm.fox.global.exception.BasicException;

public class AlreadyNameException extends BasicException {
    public AlreadyNameException() {
        super("이미 있는 이름입니다.", 400);
    }
}
