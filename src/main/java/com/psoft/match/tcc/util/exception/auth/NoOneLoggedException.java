package com.psoft.match.tcc.util.exception.auth;

public class NoOneLoggedException extends RuntimeException {

    public NoOneLoggedException() {
        super("There is no user logged.");
    }
}
