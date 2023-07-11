package com.nhat.ShoppingManagement.security.exception;

public class UnverifiedAccountException extends RuntimeException{
    public UnverifiedAccountException(String message) {
        super(message);
    }

    public UnverifiedAccountException() {

    }
}
