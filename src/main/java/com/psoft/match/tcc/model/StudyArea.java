package com.psoft.match.tcc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class StudyArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "interestedStudyAreas")
    private Collection<Student> interestedStudents;

    @JsonIgnore
    @ManyToMany(mappedBy = "interestedStudyAreas")
    private Collection<Professor> interestedProfessors;

    public StudyArea() {}

    public StudyArea(String description) {
        this.description = description;
        this.interestedStudents = new HashSet<>();
        this.interestedProfessors = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Student> getInterestedStudents() {
        return interestedStudents;
    }

    public Collection<Professor> getInterestedProfessors() {
        return interestedProfessors;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInterestedStudents(Collection<Student> interestedStudents) {
        this.interestedStudents = interestedStudents;
    }

    public void setInterestedProfessors(Collection<Professor> interestedProfessors) {
        this.interestedProfessors = interestedProfessors;
    }

    public boolean addInterestedStudent(Student student) {
        return this.interestedStudents.add(student);
    }

    public boolean removeInterestedStudent(Student student) {
        return this.interestedStudents.remove(student);
    }

    public boolean addInterestedProfessor(Professor professor) {
        return this.interestedProfessors.add(professor);
    }

    public boolean removeInterestedProfessor(Professor professor) {
        return this.interestedProfessors.remove(professor);
    }

    public void notifyStudent() {
        this.interestedStudents.forEach(student -> student.notifyNewTCC(description));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyArea that = (StudyArea) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
