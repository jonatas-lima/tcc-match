package com.psoft.match.tcc.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.OrientationInterest;
import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;

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

    @OneToMany(mappedBy = "student")
    private Collection<TCCProposal> tccProposals;

    @JsonIgnore
    @OneToOne
    private TCC tcc;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedStudyAreas;

    public Student() {}

    public Student(String fullName, String registration, String email, String expectedConclusionTerm, String username, String password) {
        super(fullName, email, username, password, UserRole.STUDENT);
        this.registration = registration;
        this.expectedConclusionTerm = expectedConclusionTerm;
        this.interestedStudyAreas = new HashSet<>();
        this.orientationIssues = new HashSet<>();
        this.orientationInterests = new HashSet<>();
        this.tccProposals = new HashSet<>();
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

    public Collection<StudyArea> getInterestedStudyAreas() {
        return interestedStudyAreas;
    }

    public boolean addInterestedArea(StudyArea studyArea) {
        return this.interestedStudyAreas.add(studyArea);
    }

    public boolean removeInterestedArea(StudyArea studyArea) {
        return this.interestedStudyAreas.remove(studyArea);
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

    public Collection<TCCProposal> getTccProposals() {
        return tccProposals;
    }

    public boolean addTccProposal(TCCProposal tccProposal) {
        return this.tccProposals.add(tccProposal);
    }

    public boolean removeOrientationInterest(TCCProposal tccProposal) {
        return this.tccProposals.remove(tccProposal);
    }

    public TCC getTcc() {
        return tcc;
    }

    public void setTcc(TCC tcc) {
        this.tcc = tcc;
    }

    public void notifyNewTCC(String studyAreaDescription) {
        System.out.printf("%s : Novo TCC cadastrado na área %s\n", getFullName(), studyAreaDescription);
    }
}
