package com.psoft.match.tcc.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.orientation.OrientationProposal;
import com.psoft.match.tcc.model.tcc.TCC;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Professor extends TCCMatchUser {

    @JsonIgnore
    @ElementCollection
    private Collection<String> labs;

	private Integer quota;

    @JsonIgnore
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedStudyAreas;

    @JsonIgnore
    @OneToMany
    private Collection<OrientationProposal> interestedTCCs;

    @JsonIgnore
    @OneToMany
    private Collection<TCC> registeredTCCs;

    public Professor() {}

    public Professor(String fullName, String email, String username, String password, Collection<String> labs, Integer quota) {
        super(fullName, email, username, password, UserRole.PROFESSOR);
        this.interestedStudyAreas = new HashSet<>();
        this.interestedTCCs = new HashSet<>();
        this.labs = labs;
        this.registeredTCCs = new HashSet<>();
        this.quota = quota;
    }

    public Collection<StudyArea> getInterestedStudyAreas() {
        return interestedStudyAreas;
    }

    public boolean addInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.add(studyArea);
    }

    public boolean removeInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.remove(studyArea);
    }

    public Collection<String> getLabs() {
        return labs;
    }

    public void addLab(String lab) {
        this.labs.add(lab);
    }

    public void removeLab(String lab) {
        this.labs.remove(lab);
    }

    public Collection<OrientationProposal> getInterestedTCCs() {
        return interestedTCCs;
    }

    public boolean addOrientationInterest(OrientationProposal orientationInterest) {
        return this.interestedTCCs.add(orientationInterest);
    }

    public boolean removeOrientationInterest(OrientationProposal orientationInterest) {
        return this.interestedTCCs.remove(orientationInterest);
    }

    public Collection<TCC> getRegisteredTCCs() {
        return new HashSet<>(registeredTCCs);
    }

    public boolean addTCC(TCC tcc) {
        return this.registeredTCCs.add(tcc);
    }

    public boolean removeTCC(TCC tcc) {
        return this.registeredTCCs.remove(tcc);
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public void decrementQuota() {
        this.quota--;
    }

    public void incrementQuota() {
        this.quota++;
    }
}
