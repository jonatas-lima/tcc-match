package com.psoft.match.tcc.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.tcc.OrientationInterest;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class Professor extends User {

    @ElementCollection
    private Collection<String> labs;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedStudyAreas;

    @OneToMany
    private Collection<OrientationInterest> interestedTCCs;

    @JsonIgnore
    @OneToMany
    private Collection<TCC> orientedTCCs;

    public Professor() {}

    public Professor(String fullName, String email, String username, String password) {
        super(fullName, email, username, password, UserRole.PROFESSOR);
        this.interestedStudyAreas = new HashSet<>();
        this.interestedTCCs = new HashSet<>();
        this.labs = new ArrayList<>();
        this.orientedTCCs = new HashSet<>();
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

    public Collection<OrientationInterest> getInterestedTCCs() {
        return interestedTCCs;
    }

    public boolean addOrientationInterest(OrientationInterest orientationInterest) {
        return this.interestedTCCs.add(orientationInterest);
    }

    public boolean removeOrientationInterest(OrientationInterest orientationInterest) {
        return this.interestedTCCs.remove(orientationInterest);
    }

    public Collection<TCC> getOrientedTCCs() {
        return new HashSet<>(orientedTCCs);
    }

    public boolean addOrientedTCC(TCC tcc) {
        return this.orientedTCCs.add(tcc);
    }

    public boolean removeOrientedTCC(TCC tcc) {
        return this.orientedTCCs.remove(tcc);
    }
}
