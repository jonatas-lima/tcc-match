package com.psoft.match.tcc.model.user;

import com.psoft.match.tcc.model.StudyArea;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Entity
public class Professor extends User {

    //private Collection<Lab> labs;
	
	private Integer quota;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "study_area_id"))
    private Collection<StudyArea> interestedStudyAreas;

    public Professor() {}

    public Professor(String fullName, String email, String username, String password) {
        super(fullName, email, username, password, UserRole.PROFESSOR);
        this.interestedStudyAreas = new HashSet<>();
        this.quota = 0;
    }

    public boolean addInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.add(studyArea);
    }

    public boolean removeInterestedStudyArea(StudyArea studyArea) {
        return this.interestedStudyAreas.remove(studyArea);
    }
    
    public Integer getQuota() {
    	return this.quota;
    }
    
    public void setQuota(Integer newQuota) {
    	this.quota = newQuota;
    }
    
    public Collection<StudyArea> getClonnedAreas() {
    	//List<StudyArea> clone = new ArrayList<StudyArea>();
    	//Collections.copy((List<StudyArea>) interestedStudyAreas, clone);
    	return this.interestedStudyAreas;
    }
}
