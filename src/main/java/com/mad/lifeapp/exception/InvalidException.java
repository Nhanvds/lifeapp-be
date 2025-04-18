package com.mad.lifeapp.exception;

import lombok.Getter;

@Getter
public class InvalidException extends Exception{
    private String msg;

    public InvalidException(String msg) {
        super();
        this.msg = msg;
    }
}
