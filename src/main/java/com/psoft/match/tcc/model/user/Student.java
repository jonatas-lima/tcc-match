package com.psoft.match.tcc.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.tcc.OrientationInterest;
import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Student extends User {

    private String registration;

    private String expectedConclusionTerm;

    @OneToMany(mappedBy = "student")
    private Collection<OrientationIssue> orientationIssues;

    @OneToMany(mappedBy = "interestedStudent")
    private Collection<OrientationInterest> orientationInterests;

    @JsonIgnore
    @OneToOne
    private TCC tcc;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedAreas;

    public Student() {}

    public Student(String fullName, String registration, String email, String expectedConclusionTerm, String username, String password) {
        super(fullName, email, username, password, UserRole.STUDENT);
        this.registration = registration;
        this.expectedConclusionTerm = expectedConclusionTerm;
        this.interestedAreas = new HashSet<>();
        this.orientationIssues = new HashSet<>();
        this.orientationInterests = new HashSet<>();
        this.tcc = null;
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

    public Collection<OrientationIssue> getOrientationIssues() {
        return orientationIssues;
    }

    public boolean addOrientationIssue(OrientationIssue orientationIssue) {
        return this.orientationIssues.add(orientationIssue);
    }

    public boolean removeOrientationIssue(OrientationIssue orientationIssue) {
        return this.orientationIssues.remove(orientationIssue);
    }

    public boolean addOrientationInterest(OrientationInterest orientationInterest) {
        return this.orientationInterests.add(orientationInterest);
    }

    public boolean removeOrientationInterest(OrientationInterest orientationInterest) {
        return this.orientationInterests.remove(orientationInterest);
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }

    public void notifyNewTCC(StudyArea studyArea) {
        System.out.printf("novo tcc com a area de estudo %s cadastrada!", studyArea.getDescription());
    }
}
