package com.psoft.match.tcc.model.tcc;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
public class TCC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private TCCStatus tccStatus;

    @ManyToMany
    private Collection<StudyArea> studyAreas;

    @ManyToOne
    private Professor professor;

    @OneToOne
    private Student student;

    private TCC(String title, String description, Professor professor, Student student) {
        this.title = title;
        this.description = description;
        this.tccStatus = TCCStatus.PENDING;
        this.studyAreas = new HashSet<>();
        this.professor = professor;
        this.student = student;
    }

    public TCC(String title, String description, Professor professor) {
        this(title, description, professor, null);
    }

    public TCC(String title, String description, Student student) {
        this(title, description, null, student);
    }

    public TCC(String title, String description) {
        this(title, description, null, null);
    }

    public TCC() {
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
        return tccStatus;
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

    public boolean removeStudyArea(StudyArea studyArea) {
        return this.studyAreas.remove(studyArea);
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isAvailable() {
        return this.student == null;
    }

    public void notifyStudent() {
        this.student.notify();
    }
}
