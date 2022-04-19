package com.dsm.fox.global.exception.exceptions;

import com.dsm.fox.global.exception.BasicException;

public class PhraseNotFoundException extends BasicException {
    public PhraseNotFoundException() {
        super("글귀를 찾을 수 없습니다", 404);
    }
}
