package com.psoft.match.tcc.util.exception.professor;

import com.psoft.match.tcc.util.exception.common.ConflictException;

public class ProfessorAlreadyInterestedInStudyAreaException extends ConflictException {

    private static final String PROFESSOR_ALREADY_INTERESTED_IN_STUDY_AREA_MSG = "Professor %s is already interested in study area with description %s";

    public ProfessorAlreadyInterestedInStudyAreaException(String professorName, String studyAreaDescription) {
        super(String.format(PROFESSOR_ALREADY_INTERESTED_IN_STUDY_AREA_MSG, professorName, studyAreaDescription));
    }
}