package com.psoft.match.tcc.util.exception.tcc;

public class TCCDoesNotBelongToStudentException extends RuntimeException {

    private static final String TCC_DOES_NOT_BELONG_TO_STUDENT_MSG = "The TCC with id %d does not belong to student %s";

    public TCCDoesNotBelongToStudentException(String studentName, Long tccId) {
        super(String.format(TCC_DOES_NOT_BELONG_TO_STUDENT_MSG, studentName, tccId));
    }
}
