package com.dsm.fox.global.exception.exceptions;

import com.dsm.fox.global.exception.BasicException;

public class PasswordNoMatchedException extends BasicException {
    public PasswordNoMatchedException() {
        super("비밀번호가 일치하지 않습니다.", 400);
    }
}
