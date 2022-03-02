package com.psoft.match.tcc.model.user;

import com.psoft.match.tcc.model.StudyArea;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Student extends User {

    private String fullName;

    private String registration;

    private String email;

    private String expectedConclusionTerm;

    @ManyToMany
    private Collection<StudyArea> interestedAreas;

    public Student() {}

    public Student(String fullName, String registration, String email, String expectedConclusionTerm, String username, String password) {
        this.fullName = fullName;
        this.registration = registration;
        this.email = email;
        this.expectedConclusionTerm = expectedConclusionTerm;
        this.interestedAreas = new HashSet<>();
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(UserRole.COMMON_USER);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
