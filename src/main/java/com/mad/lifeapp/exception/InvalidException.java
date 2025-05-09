package com.mad.lifeapp.exception;

import lombok.Getter;

@Getter
public class InvalidException extends Exception{
    public InvalidException(String msg) {
        super(msg);
    }
}
