package com.dsm.fox.global.exception.exceptions;

import com.dsm.fox.global.exception.BasicException;

public class PostNotFoundException extends BasicException {
    public PostNotFoundException() {
        super("게시글을 찾을 수 없습니다.",404 );
    }
}
