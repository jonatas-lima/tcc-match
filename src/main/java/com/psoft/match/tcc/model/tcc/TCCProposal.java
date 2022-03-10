package com.psoft.match.tcc.model.tcc;

import com.psoft.match.tcc.model.StudyArea;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class TCCProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private TCCStatus tccStatus;

    @ManyToMany
    private Collection<StudyArea> studyAreas;

    public TCCProposal() {}

    public TCCProposal(String title, String description) {
        this.title = title;
        this.description = description;
        this.tccStatus = TCCStatus.PENDING;
        this.studyAreas = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TCCStatus getTccStatus() {
        return this.tccStatus;
    }

    public void setTccStatus(TCCStatus tccStatus) {
        this.tccStatus = tccStatus;
    }

    public Collection<StudyArea> getStudyAreas() {
        return studyAreas;
    }

    public boolean addStudyArea(StudyArea studyArea) {
        return this.studyAreas.add(studyArea);
    }
}
