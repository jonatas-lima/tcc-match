package com.psoft.match.tcc.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCStatus;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "tcc_id")
    )
    private Set<TCC> interestedTCCs;

    @JsonIgnore
    @OneToMany(mappedBy = "advisor")
    private Set<TCC> registeredTCCs;
    
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

    public Collection<TCC> getInterestedTCCs() {
        return interestedTCCs;
    }

    public Collection<TCC> getTCCOrientations() {
        return this.registeredTCCs.stream().filter(tcc -> tcc.getTccStatus().equals(TCCStatus.ON_GOING)).collect(Collectors.toList());
    }

    public boolean addOrientationInterest(TCC orientationInterest) {
        return this.interestedTCCs.add(orientationInterest);
    }

    public boolean removeOrientationInterest(TCC orientationInterest) {
        return this.interestedTCCs.remove(orientationInterest);
    }

    public Collection<TCC> getRegisteredTCCs() {
        return new HashSet<>(registeredTCCs);
    }

    public boolean registerTCC(TCC tcc) {
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

    public boolean isAvailable() {
        return this.getTCCOrientations().size() < this.quota;
    }

    public boolean hasSharedInterestedWith(Student student) {
        for (StudyArea s : student.getInterestedStudyAreas()) {
            if (this.getInterestedStudyAreas().contains(s)) {
                return true;
            }
        }
        return false;
    }

    @PreRemove
    private void preRemove() {
        this.registeredTCCs.forEach(tcc -> {
            tcc.setAdvisor(null);
            tcc.setTccStatus(TCCStatus.PENDING);
            tcc.setTerm(null);
        });
        this.interestedTCCs.forEach(tcc -> tcc.removeOrientationInterest(this));
    }
}
