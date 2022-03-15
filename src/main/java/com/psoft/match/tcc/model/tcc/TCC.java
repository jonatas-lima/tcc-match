package com.psoft.match.tcc.model.tcc;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.orientation.OrientationInterest;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class TCC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Professor professor;

    @OneToOne
    private TCCProposal tccProposal;

    private TCCStatus tccStatus;

    @OneToMany
    Collection<OrientationInterest> orientationInterests;

    public TCC(TCCProposal tccProposal, Professor professor) {
        this.tccProposal = tccProposal;
        this.professor = professor;
        this.tccStatus = TCCStatus.PENDING;
    }

    public TCC() {
    }

    public Long getId() {
        return id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public boolean isAvailable() {
        return this.professor == null || this.getStudent() == null;
    }

    public String toEmailFormat() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("[INFORMAÇÕES]\n")
                .append("Título: ").append(getTitle()).append("\n")
                .append("Professor: ").append(getProfessor().getFullName()).append("\n");

        getStudyAreas().forEach(studyArea -> sb.append("- ").append(studyArea.getDescription()).append("\n"));

        return sb.toString();
    }

    public Student getStudent() {
        return tccProposal.getStudent();
    }

    public String getTitle() {
        return tccProposal.getTitle();
    }

    public Collection<StudyArea> getStudyAreas() {
        return tccProposal.getStudyAreas();
    }

    public Collection<OrientationInterest> getOrientationInterests() {
        return orientationInterests;
    }

    public boolean addOrientationInterest(OrientationInterest orientationInterest) {
        return this.orientationInterests.add(orientationInterest);
    }

    public boolean removeOrientationInterest(OrientationInterest orientationInterest) {
        return this.orientationInterests.remove(orientationInterest);
    }

    public TCCProposal getTccProposal() {
        return tccProposal;
    }

    public void setTccProposal(TCCProposal tccProposal) {
        this.tccProposal = tccProposal;
    }

    public TCCStatus getTccStatus() {
        return tccStatus;
    }

    public void approveTCC() {
        this.tccStatus = TCCStatus.APPROVED;
    }
}