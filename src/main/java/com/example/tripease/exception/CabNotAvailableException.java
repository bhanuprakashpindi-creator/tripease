package com.example.tripease.exception;

import org.aspectj.bridge.IMessage;

public class CabNotAvailableException extends RuntimeException{
    public CabNotAvailableException(String message)
    {
        super(message);
    }
}
