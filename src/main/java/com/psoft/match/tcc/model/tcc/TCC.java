package com.psoft.match.tcc.model.tcc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class TCC {

    @Id
    @Column(unique = true)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String term;

    @ManyToOne
    private Student advisedStudent;

    @ManyToOne
    private Professor advisor;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "tcc_id"),
            inverseJoinColumns = @JoinColumn(name = "study_area_id")
    )
    private Collection<StudyArea> studyAreas;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "tcc_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Collection<Student> interestedStudents;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "tcc_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private Collection<Professor> interestedProfessors;

    private TCCStatus tccStatus;

    public TCC() {
    }

    public TCC(Professor advisor, String title, String description, Student advisedStudent, Collection<StudyArea> studyAreas, Collection<Student> interestedStudents, TCCStatus tccStatus, Collection<Professor> interestedProfessors, String term) {
        this.advisor = advisor;
        this.title = title;
        this.description = description;
        this.advisedStudent = advisedStudent;
        this.studyAreas = studyAreas;
        this.interestedStudents = interestedStudents;
        this.tccStatus = tccStatus;
        this.interestedProfessors = interestedProfessors;
        this.term = term;
    }

    public TCC(Professor advisor, String title, String description) {
        this(advisor, title, description, null, new HashSet<>(), new HashSet<>(), TCCStatus.APPROVED, new HashSet<>(), null);
    }

    public TCC(Student advisedStudent, String title, String description) {
        this(null, title, description, advisedStudent, new HashSet<>(), new HashSet<>(), TCCStatus.PENDING, new HashSet<>(), null);
    }

    public Long getId() {
        return id;
    }

    public Professor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Professor advisor) {
        this.advisor = advisor;
    }

    @JsonIgnore
    public boolean isAvailable() {
        return this.advisor == null || this.advisedStudent == null;
    }

    public String toEmailFormat() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("[INFORMAÇÕES]\n")
                .append("Título do TCC: ").append(getTitle()).append("\n")
                .append("Professor: ").append(advisor.getFullName()).append("\n");

        getStudyAreas().forEach(studyArea -> sb.append("- ").append(studyArea.getDescription()).append("\n"));

        return sb.toString();
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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Student getAdvisedStudent() {
        return advisedStudent;
    }

    public void setAdvisedStudent(Student student) {
        this.advisedStudent = student;
    }

    public Collection<StudyArea> getStudyAreas() {
        return studyAreas;
    }

    public void setStudyAreas(Collection<StudyArea> studyAreas) {
        this.studyAreas = studyAreas;
    }

    public Collection<Student> getInterestedStudents() {
        return interestedStudents;
    }

    public void setTccStatus(TCCStatus tccStatus) {
        this.tccStatus = tccStatus;
    }

    public Collection<Professor> getInterestedProfessors() {
        return interestedProfessors;
    }

    public boolean addOrientationInterest(Professor interestedProfessor) {
        return this.interestedProfessors.add(interestedProfessor);
    }

    public boolean addOrientationInterest(Student interestedStudent) {
        return this.interestedStudents.add(interestedStudent);
    }

    public boolean removeOrientationInterest(Student interestedStudent) {
        return this.interestedStudents.remove(interestedStudent);
    }

    public boolean removeOrientationInterest(Professor interestedProfessor) {
        return this.interestedProfessors.remove(interestedProfessor);
    }

    public boolean addStudyArea(StudyArea studyArea) {
        return this.studyAreas.add(studyArea);
    }

    public boolean removeStudyArea(StudyArea studyArea) {
        return this.studyAreas.remove(studyArea);
    }

    public TCCStatus getTccStatus() {
        return tccStatus;
    }

    public void approveTCC() {
        this.tccStatus = TCCStatus.ON_GOING;
    }

    public boolean isCreatedByStudent() {
        return this.advisedStudent != null && advisor == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TCC tcc = (TCC) o;
        return Objects.equals(id, tcc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}