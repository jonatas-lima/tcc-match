package com.psoft.match.tcc.util.exception.student;

import com.psoft.match.tcc.util.exception.common.ConflictException;

public class StudentDoesNotHaveOrientationInterestException extends ConflictException {
    private static final String STUDENT_DOES_NOT_HAVE_ORIENTATION_INTEREST = "Student %s does not have interest";

    public StudentDoesNotHaveOrientationInterestException(String studentName) {
        super(String.format(STUDENT_DOES_NOT_HAVE_ORIENTATION_INTEREST));
    }
}
