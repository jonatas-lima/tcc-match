package com.psoft.match.tcc.model.user;

import com.psoft.match.tcc.model.StudyArea;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Professor extends User {

    private String fullName;

    private String email;

    //private Collection<Lab> labs;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedStudyAreas;

    public Professor() {}

    public Professor(String fullName, String email, String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.interestedStudyAreas = new HashSet<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean addInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.add(studyArea);
    }

    public boolean removeInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.remove(studyArea);
    }
}
