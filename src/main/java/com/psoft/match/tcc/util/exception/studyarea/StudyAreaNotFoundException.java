package com.psoft.match.tcc.util.exception.studyarea;

public class StudyAreaNotFoundException extends RuntimeException{
    private static final String STUDY_AREA_NOT_FOUND_MSG = "Study area with id %s not found.";

    public StudyAreaNotFoundException(Long id) {
        super(String.format(STUDY_AREA_NOT_FOUND_MSG, id));
    }
}
