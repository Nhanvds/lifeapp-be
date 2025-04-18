package com.mad.lifeapp.exception;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends Exception{
    private String msg;
    public InvalidCredentialsException(String msg){
        super();
        this.msg=msg;
    }
}