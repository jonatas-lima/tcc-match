package com.psoft.match.tcc.model.user;

import com.psoft.match.tcc.model.StudyArea;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Professor extends User {

    //private Collection<Lab> labs;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedStudyAreas;

    public Professor() {}

    public Professor(String fullName, String email, String username, String password) {
        super(fullName, email, username, password, UserRole.PROFESSOR);
        this.interestedStudyAreas = new HashSet<>();
    }

    public boolean addInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.add(studyArea);
    }

    public boolean removeInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.remove(studyArea);
    }
}
