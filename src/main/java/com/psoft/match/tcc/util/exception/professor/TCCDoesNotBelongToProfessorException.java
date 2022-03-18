package com.psoft.match.tcc.util.exception.professor;

import com.psoft.match.tcc.util.exception.common.UnauthorizedException;

public class TCCDoesNotBelongToProfessorException extends UnauthorizedException {
    private static final String TCC_DOES_NOT_BELONG_TO_PROFESSOR_MSG = "The TCC with id %d does not belong to professor %s";

    public TCCDoesNotBelongToProfessorException(Long tccId, String professorName) {
        super(String.format(TCC_DOES_NOT_BELONG_TO_PROFESSOR_MSG, tccId, professorName));
    }
}