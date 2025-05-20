package com.mad.lifeapp.exception;

import lombok.Getter;

@Getter
public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(String msg){
        super(msg);
    }
}