package com.psoft.match.tcc.dto;

import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;

import java.util.Collection;

public class StudyAreaDTO {

    private String description;

    private Collection<Student> interestedStudents;

    private Collection<Professor> interestedProfessors;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Student> getInterestedStudents() {
        return interestedStudents;
    }

    public void setInterestedStudents(Collection<Student> interestedStudents) {
        this.interestedStudents = interestedStudents;
    }

    public Collection<Professor> getInterestedProfessors() {
        return interestedProfessors;
    }

    public void setInterestedProfessors(Collection<Professor> interestedProfessors) {
        this.interestedProfessors = interestedProfessors;
    }
}
