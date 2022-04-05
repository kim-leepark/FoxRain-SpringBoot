package com.dsm.fox.global.exception.exceptions;

import com.dsm.fox.global.exception.BasicException;

public class UserNotFoundException extends BasicException {
    public UserNotFoundException() {
        super("어드민 아이디를 찾을 수 없습니다.", 404);
    }
}
