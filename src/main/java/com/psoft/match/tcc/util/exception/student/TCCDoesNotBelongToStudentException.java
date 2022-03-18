package com.psoft.match.tcc.util.exception.student;

import com.psoft.match.tcc.util.exception.common.UnauthorizedException;

public class TCCDoesNotBelongToStudentException extends UnauthorizedException {

    private static final String TCC_DOES_NOT_BELONG_TO_STUDENT_MSG = "The TCC with id %d does not belong to student %s";

    public TCCDoesNotBelongToStudentException(String studentName, Long tccId) {
        super(String.format(TCC_DOES_NOT_BELONG_TO_STUDENT_MSG, studentName, tccId));
    }
}
