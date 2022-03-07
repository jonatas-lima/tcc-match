package com.psoft.match.tcc.model.user;

import com.psoft.match.tcc.model.StudyArea;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Student extends User {

    private String registration;

    private String expectedConclusionTerm;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedAreas;

    public Student() {}

    public Student(String fullName, String registration, String email, String expectedConclusionTerm, String username, String password) {
        super(fullName, email, username, password, UserRole.COMMON_USER);
        this.registration = registration;
        this.expectedConclusionTerm = expectedConclusionTerm;
        this.interestedAreas = new HashSet<>();
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getExpectedConclusionTerm() {
        return expectedConclusionTerm;
    }

    public void setExpectedConclusionTerm(String expectedConclusionTerm) {
        this.expectedConclusionTerm = expectedConclusionTerm;
    }

    public Collection<StudyArea> getInterestedAreas() {
        return interestedAreas;
    }

    public boolean addInterestedArea(StudyArea studyArea) {
        return this.interestedAreas.add(studyArea);
    }

    public boolean removeInterestedArea(StudyArea studyArea) {
        return this.interestedAreas.remove(studyArea);
    }
}
