package com.psoft.match.tcc.util.exception.studyarea;

public class StudyAreaAlreadyExistsException extends RuntimeException {

    private static final String STUDY_AREA_ALREADY_EXISTS_MSG = "Study area with description %s already exists.";

    public StudyAreaAlreadyExistsException(String description) {
        super(String.format(STUDY_AREA_ALREADY_EXISTS_MSG, description));
    }
}
