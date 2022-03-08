package com.psoft.match.tcc.util.exception.auth;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {
        super("Wrong username or password");
    }
}
