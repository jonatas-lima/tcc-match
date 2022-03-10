package com.psoft.match.tcc.util.exception.tcc;

public class UnavailableTCCException extends RuntimeException {

    private static final String UNAVAILABLE_TCC_MSG = "The TCC with id %d is not available.";

    public UnavailableTCCException(Long id) {
        super(String.format(UNAVAILABLE_TCC_MSG, id));
    }
}
