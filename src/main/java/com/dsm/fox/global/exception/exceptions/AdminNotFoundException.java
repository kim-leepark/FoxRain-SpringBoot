package com.dsm.fox.global.exception.exceptions;

import com.dsm.fox.global.exception.BasicException;

public class AdminNotFoundException extends BasicException {
    public AdminNotFoundException() {
        super("어드민 아이디를 찾을 수 없습니다.", 404);
    }
}
