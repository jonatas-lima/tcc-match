package com.psoft.match.tcc.model.tcc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.orientation.OrientationProposal;
import com.psoft.match.tcc.model.user.Student;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class TCCProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @JsonIgnore
    @ManyToOne
    private Student student;

    @JsonIgnore
    @ManyToMany
    private Collection<StudyArea> studyAreas;

    @OneToMany
    private Collection<OrientationProposal> orientationProposals;

    public TCCProposal() {}

    public TCCProposal(String title, String description) {
        this(null, title, description, new HashSet<>());
    }

    public TCCProposal(String title, String description, Collection<StudyArea> studyAreas) {
        this(null, title, description, studyAreas);
    }

    public TCCProposal(Student student, String title, String description, Collection<StudyArea> studyAreas) {
        this.title = title;
        this.description = description;
        this.studyAreas = studyAreas;
        this.student = student;
        this.orientationProposals = new HashSet<>();
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

    public Collection<StudyArea> getStudyAreas() {
        return studyAreas;
    }

    public boolean addStudyArea(StudyArea studyArea) {
        return this.studyAreas.add(studyArea);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
