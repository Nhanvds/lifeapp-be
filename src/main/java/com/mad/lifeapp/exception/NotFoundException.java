package com.mad.lifeapp.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception{
    private String msg;

    public NotFoundException(String msg) {
        super();
        this.msg = msg;
    }
}
